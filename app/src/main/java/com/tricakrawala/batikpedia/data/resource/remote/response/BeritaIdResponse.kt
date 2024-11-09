package com.tricakrawala.batikpedia.data.resource.remote.response

import com.google.gson.annotations.SerializedName

data class BeritaIdResponse(

	@field:SerializedName("values")
	val values: BeritaId
)

data class BeritaId(

	@field:SerializedName("tglBerita")
	val tglBerita: String,

	@field:SerializedName("imageBerita")
	val imageBerita: String,

	@field:SerializedName("namaBerita")
	val namaBerita: String,

	@field:SerializedName("lokasiBerita")
	val lokasiBerita: String,

	@field:SerializedName("urlBerita")
	val urlBerita: String,

	@field:SerializedName("idBerita")
	val idBerita: Int
)
