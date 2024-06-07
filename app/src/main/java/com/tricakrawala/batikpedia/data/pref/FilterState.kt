package com.tricakrawala.batikpedia.data.pref

data class FilterState(
    val sort : String = "asc",
    val wilayah : String = "Semua",
    val jenisBatik : String = "Semua"
)
