package com.tricakrawala.batikpedia.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.tricakrawala.batikpedia.BuildConfig
import com.tricakrawala.batikpedia.presentation.navigation.Screen
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {


    val listScreenWithoutBottomBar = listOf(
        Screen.MainSplash.route,
        Screen.ToListProvinsi.route,
        Screen.DetailProvinsi.route,
        Screen.Berita.route,
        Screen.DetailBatik.route,
        Screen.DetailWisataByProvinsi.route,
        Screen.DetailBatikFull.route,
        Screen.DetailEdukasi.route,
        Screen.DetailWisata.route,
        Screen.Filter.route,
        Screen.ToListKursus.route,
        Screen.DetailKursus.route,
        Screen.VideoEdukasi.route,
        Screen.Camera.route,
        Screen.DetailBerita.route
    )

    val wilayah = listOf("Bali", "Cirebon", "Jawa Barat", "Jawa Tengah", "Jawa Timur", "Nusa Tenggara Barat", "Solo", "Sumatera", "Sulawesi Selatan", "Surakarta", "Yogyakarta")


    private const val FILENAME_FORMAT = "yyyyMMdd_HHmmss"
    private val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())
    private const val MAXIMAL_SIZE = 1000000


    fun captureImage(imageCapture: ImageCapture, context: Context, onImageCaptured: (Bitmap) -> Unit) {
        val name = "CameraxImage_${System.currentTimeMillis()}.jpeg"
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image")
            }
        }
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(
                context.contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            .build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri ?: return
                    val file = uriToFile(savedUri, context)

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        val reducedFile = file.reduceFileImage()
                        val bitmap = BitmapFactory.decodeFile(reducedFile.path)
                        onImageCaptured(bitmap)
                    } else {
                        val bitmap = BitmapFactory.decodeFile(file.path)
                        onImageCaptured(bitmap)
                    }

                    println("Image saved successfully: $savedUri")
                }

                override fun onError(exception: ImageCaptureException) {
                    println("Failed to save image: ${exception.message}")
                }
            }
        )
    }


    fun getImageUri(context: Context): Uri {
        var uri: Uri? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, "$timeStamp.jpg")
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/MyCamera/")
            }
            uri = context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )

        }
        return uri ?: getImageUriForPreQ(context)
    }

    private fun getImageUriForPreQ(context: Context): Uri {
        val filesDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File(filesDir, "/MyCamera/$timeStamp.jpg")
        if (imageFile.parentFile?.exists() == false) imageFile.parentFile?.mkdir()
        return FileProvider.getUriForFile(
            context,
            "${BuildConfig.APPLICATION_ID}.fileprovider",
            imageFile
        )
    }

    fun createCustomTempFile(context: Context): File {
        val filesDir = context.externalCacheDir
        return File.createTempFile(timeStamp, ".jpg", filesDir)
    }

    fun uriToFile(imageUri: Uri, context: Context): File {
        val myFile = createCustomTempFile(context)
        val inputStream = context.contentResolver.openInputStream(imageUri) as InputStream
        val outputStream = FileOutputStream(myFile)
        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } > 0) outputStream.write(buffer, 0, length)
        outputStream.close()
        inputStream.close()
        return myFile
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun File.reduceFileImage(): File {
        val file = this
        val bitmap = BitmapFactory.decodeFile(file.path).getRotatedBitmap(file)
        var compressQuality = 100
        var streamLength: Int
        do {
            val bmpStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
            compressQuality -= 5
        } while (streamLength > MAXIMAL_SIZE)
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))
        return file
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun Bitmap.getRotatedBitmap(file: File): Bitmap {
        val orientation = ExifInterface(file).getAttributeInt(
            ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED
        )
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(this, 90F)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(this, 180F)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(this, 270F)
            ExifInterface.ORIENTATION_NORMAL -> this
            else -> this
        }
    }

    fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            source, 0, 0, source.width, source.height, matrix, true
        )
    }

}