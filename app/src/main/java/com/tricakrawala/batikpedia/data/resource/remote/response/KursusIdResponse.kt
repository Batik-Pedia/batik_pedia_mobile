package com.tricakrawala.batikpedia.data.resource.remote.response

import com.google.gson.annotations.SerializedName

data class KursusIdResponse(

	@field:SerializedName("values")
	val values: KursusId
)

data class KursusId(

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
