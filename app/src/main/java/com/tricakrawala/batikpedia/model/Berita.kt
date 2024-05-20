package com.tricakrawala.batikpedia.model

data class Berita(
    val idBerita : Long,
    val url : String,
    val title : String,
    val image : Int,
    val time : String,
    val lokasi : String,
)
