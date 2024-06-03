package com.tricakrawala.batikpedia.data.resource.remote.response

import com.google.gson.annotations.SerializedName

data class KatalogResponse(

	@field:SerializedName("values")
	val values: Values
)

data class KatalogBatikItem(

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("namaBatik")
	val namaBatik: String,

	@field:SerializedName("penggunaan")
	val penggunaan: String,

	@field:SerializedName("makna")
	val makna: String,

	@field:SerializedName("lon")
	val lon: String,

	@field:SerializedName("detailBatik")
	val detailBatik: String,

	@field:SerializedName("wilayah")
	val wilayah: String,

	@field:SerializedName("idBatik")
	val idBatik: Int,

	@field:SerializedName("sejarahBatik")
	val sejarahBatik: String,

	@field:SerializedName("lat")
	val lat: String,

	@field:SerializedName("jenisBatik")
	val jenisBatik: String
)

data class Values(

	@field:SerializedName("katalogBatik")
	val katalogBatik: List<KatalogBatikItem>,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("status")
	val status: Int
)
