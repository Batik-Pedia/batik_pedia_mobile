package com.tricakrawala.restapibatikpedia.data.remote.response

import com.google.gson.annotations.SerializedName

data class BeritaResponse(

	@field:SerializedName("values")
	val values: Values
)

data class Values(

    @field:SerializedName("author")
	val author: String,

    @field:SerializedName("berita")
	val berita: List<BeritaItem>,

    @field:SerializedName("status")
	val status: Int
)

data class BeritaItem(

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
