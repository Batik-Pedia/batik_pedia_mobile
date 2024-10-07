package com.tricakrawala.batikpedia.utils

import android.net.Uri
import com.tricakrawala.batikpedia.presentation.navigation.Screen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {

    const val BASE_URL = "#"

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