package com.tricakrawala.batikpedia.data.resource.remote.response

import com.google.gson.annotations.SerializedName

data class RekomendasiResponse(

	@field:SerializedName("values")
	val values: RekomendasiValues
)

data class RekomendasiItem(

	@field:SerializedName("idRekomendasi")
	val idRekomendasi: Int,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("link")
	val link: String
)

data class RekomendasiValues(

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("rekomendasi")
	val rekomendasi: List<RekomendasiItem>,

	@field:SerializedName("status")
	val status: Int
)
