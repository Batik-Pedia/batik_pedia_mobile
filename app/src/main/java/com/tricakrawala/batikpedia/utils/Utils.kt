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

    const val BASE_URL = "https://forradi.sga.dom.my.id/"

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

    val wilayah = listOf("Semua","Bali", "Cirebon", "Jawa Barat", "Jawa Tengah", "Jawa Timur", "Nusa Tenggara Barat", "Solo", "Sumatera", "Sulawesi Selatan", "Surakarta", "Yogyakarta")


    private const val FILENAME_FORMAT = "yyyyMMdd_HHmmss"
    private val timeStamp: String = SimpleDateFormat(FILENAME_FORMAT, Locale.US).format(Date())
    private const val MAXIMAL_SIZE = 1000000


    fun extractYoutubeVideoId(url: String): String? {
        return Uri.parse(url)?.getQueryParameter("v")
    }

    val labels = listOf(
        "Batik Geblek Renteng",
                "Batik Kawung",
                "Batik Gentongan",
                "Batik Simbut",
               " Batik Megamemendung",
                "Batik Dayak",
                "Batik Pring Sedapur",
                "Batik Parang",
                "Batik Tujuh Rupa",
                "Batik Kraton",
                "Batik Ikat Celup",
                "Batik Corak Insang"

    )

}