package com.tricakrawala.batikpedia.presentation.ui.screen.camera

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.content.ContextCompat
import com.tricakrawala.batikpedia.ml.ModelUnquant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
fun captureImage(
    imageCapture: ImageCapture,
    context: Context,
    onImageCaptured: (Bitmap, String) -> Unit,
) {
    val outputOptions = ImageCapture.OutputFileOptions.Builder(
        context.contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "image_${System.currentTimeMillis()}.jpg")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        }
    ).build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = outputFileResults.savedUri ?: return

                // Run the image processing and model inference in a background coroutine
                CoroutineScope(Dispatchers.IO).launch {
                    val bitmap = BitmapFactory.decodeStream(context.contentResolver.openInputStream(savedUri))
                    val rotatedBitmap = correctImageOrientation(context, savedUri, bitmap)
                    val croppedBitmap = cropToSquare(rotatedBitmap)
                    val predictionResult = runModelInference(croppedBitmap, context)

                    withContext(Dispatchers.Main) {
                        onImageCaptured(croppedBitmap, predictionResult)
                    }
                }
            }

            override fun onError(exception: ImageCaptureException) {
                println("Failed to save image: ${exception.message}")
            }
        }
    )
}


fun runModelInference(bitmap: Bitmap, context: Context): String {
    val model = ModelUnquant.newInstance(context)

    // Resize the bitmap to match the expected input shape of the model
    val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true)

    // Creates inputs for reference.
    val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
    val tensorImage = TensorImage(DataType.FLOAT32)
    tensorImage.load(resizedBitmap)
    inputFeature0.loadBuffer(tensorImage.buffer)

    // Runs model inference and gets result.
    val outputs = model.process(inputFeature0)
    val outputFeature0 = outputs.outputFeature0AsTensorBuffer


    val labels = context.assets.open("labels.txt").bufferedReader().readLines()

    val outputArray = outputFeature0.floatArray
    var maxIdx = 0
    outputArray.forEachIndexed{ index, fl ->
        if(fl > outputArray[maxIdx]){
            maxIdx = index
        }

    }
    val result = labels[maxIdx]
    return result
}

fun correctImageOrientation(context: Context, imageUri: Uri, bitmap: Bitmap): Bitmap {
    val inputStream = context.contentResolver.openInputStream(imageUri)
    val exifInterface = ExifInterface(inputStream!!)
    val orientation = exifInterface.getAttributeInt(
        ExifInterface.TAG_ORIENTATION,
        ExifInterface.ORIENTATION_UNDEFINED
    )
    return when (orientation) {
        ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90f)
        ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180f)
        ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270f)
        ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> flipImage(bitmap, horizontal = true, vertical = false)
        ExifInterface.ORIENTATION_FLIP_VERTICAL -> flipImage(bitmap, horizontal = false, vertical = true)
        else -> bitmap
    }
}

fun rotateImage(bitmap: Bitmap, degree: Float): Bitmap {
    val matrix = Matrix().apply { postRotate(degree) }
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}

fun flipImage(bitmap: Bitmap, horizontal: Boolean, vertical: Boolean): Bitmap {
    val matrix = Matrix().apply {
        postScale(
            if (horizontal) -1f else 1f,
            if (vertical) -1f else 1f,
            bitmap.width / 2f,
            bitmap.height / 2f
        )
    }
    return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
}

fun cropToSquare(bitmap: Bitmap): Bitmap {
    val width = bitmap.width
    val height = bitmap.height
    val newWidth = if (width > height) height else width
    val newHeight = if (width > height) height else width

    val cropW = (width - newWidth) / 2
    val cropH = (height - newHeight) / 2

    return Bitmap.createBitmap(bitmap, cropW, cropH, newWidth, newHeight)
}