package com.tricakrawala.batikpedia.data.resource.remote.response

import com.google.gson.annotations.SerializedName

data class WisataIdResponse(

	@field:SerializedName("values")
	val values: WisataId
)

data class WisataId(

	@field:SerializedName("idWisata")
	val idWisata: Int,

	@field:SerializedName("namaWisata")
	val namaWisata: String,

	@field:SerializedName("imageWisata")
	val imageWisata: String,

	@field:SerializedName("lon")
	val lon: String,

	@field:SerializedName("detailWisata")
	val detailWisata: String,

	@field:SerializedName("lat")
	val lat: String
)
