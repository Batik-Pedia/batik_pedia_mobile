package com.tricakrawala.batikpedia.data.resource.remote.response

import com.google.gson.annotations.SerializedName

data class WisataResponse(

	@field:SerializedName("values")
	val values: Wisata
)

data class Wisata(

	@field:SerializedName("wisata")
	val wisata: List<WisataItem>,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("status")
	val status: Int
)

data class WisataItem(

	@field:SerializedName("idWisata")
	val idWisata: Int,

	@field:SerializedName("namaWisata")
	val namaWisata: String,

	@field:SerializedName("imageWisata")
	val imageWisata: String,

	@field:SerializedName("lon")
	val lon: Any,

	@field:SerializedName("wilayah")
	val wilayah: String,

	@field:SerializedName("detailWisata")
	val detailWisata: String,

	@field:SerializedName("lat")
	val lat: Any
)
