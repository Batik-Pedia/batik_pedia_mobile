package com.tricakrawala.batikpedia.data.resource.remote.response

import com.google.gson.annotations.SerializedName

data class KatalogIdResponse(

	@field:SerializedName("values")
	val values: KatalogId
)

data class KatalogId(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("namaBatik")
	val namaBatik: String,

	@field:SerializedName("penggunaan")
	val penggunaan: String,

	@field:SerializedName("makna")
	val makna: String,

	@field:SerializedName("lon")
	val lon: Double,

	@field:SerializedName("detailBatik")
	val detailBatik: String,

	@field:SerializedName("wilayah")
	val wilayah: String,

	@field:SerializedName("idBatik")
	val idBatik: Int,

	@field:SerializedName("sejarahBatik")
	val sejarahBatik: String,

	@field:SerializedName("lat")
	val lat: Double,

	@field:SerializedName("jenisBatik")
	val jenisBatik: String
)
