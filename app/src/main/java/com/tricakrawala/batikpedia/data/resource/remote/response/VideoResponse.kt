package com.tricakrawala.batikpedia.data.resource.remote.response

import com.google.gson.annotations.SerializedName

data class VideoResponse(

	@field:SerializedName("values")
	val values: List<ValuesItem>
)

data class ValuesItem(

	@field:SerializedName("idVideo")
	val idVideo: Int,

	@field:SerializedName("urlVideo")
	val urlVideo: String,

	@field:SerializedName("imgVideo")
	val imgVideo: String,

	@field:SerializedName("judulVideo")
	val judulVideo: String
)
