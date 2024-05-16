package com.tricakrawala.batikpedia.navigation

sealed class Screen(val route : String) {
    object Home : Screen("home")
    object SplashFirst : Screen("splashfirst")
    object SplashSecond : Screen("splashsecond")
    object SplashThird : Screen("splashthird")
    object ToListProvinsi : Screen("tolistprovinsi")
    object Berita : Screen("berita")
    object DetailProvinsi : Screen("toListProvinsi/{idNusantara}"){
        fun createRoute(idNusantara : Long) = "toListProvinsi/$idNusantara"
    }

    object DetailWisataByProvinsi : Screen("toListProvinsi/{idNusantara}/{idWisata}"){
        fun createRoute(idNusantara: Long, idWisata : Long) = "toListProvinsi/$idNusantara/$idWisata"

    }


    object Katalog : Screen("katalog")

    object DetailBatik : Screen("katalog/{idBatik}"){
        fun createRoute(idBatik : Long) = "katalog/$idBatik"
    }

    object DetailBatikFull : Screen("katalog/{idBatik}/{idBatikFull}"){
        fun createRoute(idBatik : Long, idBatikFull : Long) = "katalog/$idBatik/$idBatikFull"
    }
    object Edukasi : Screen("edukasi")

    object Wisata : Screen("wisata")
    object DetailKursus : Screen("edukasi/{idKursus}"){
        fun createRoute(idKursus: Long) = "edukasi/$idKursus"
    }
    object DetailEdukasi : Screen ("edukasi/{idKursus}"){
        fun createRoute(idKursus : Long) = "edukasi/$idKursus"
    }
    object ToListKursus : Screen("tolistkursus")
    object VideoEdukasi : Screen("videoedukasi")
    object DetailWisata : Screen("wisata/{idWisata}"){
        fun createRoute(idWisata: Long) = "wisata/$idWisata"
    }


    object Filter : Screen("filter")

    object Camera : Screen("camera")
}
