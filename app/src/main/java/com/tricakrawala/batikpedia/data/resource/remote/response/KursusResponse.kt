package com.tricakrawala.batikpedia.data.resource.remote.response

import com.google.gson.annotations.SerializedName

data class KursusResponse(

	@field:SerializedName("values")
	val values: ListKursus
)

data class KursusItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("harga")
	val harga: String,

	@field:SerializedName("urlKursus")
	val urlKursus: String,

	@field:SerializedName("idKursus")
	val idKursus: Int,

	@field:SerializedName("namaKursus")
	val namaKursus: String,

	@field:SerializedName("deskripsi")
	val deskripsi: String
)

data class ListKursus(

	@field:SerializedName("kursus")
	val kursus: List<KursusItem>,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("status")
	val status: Int
)
