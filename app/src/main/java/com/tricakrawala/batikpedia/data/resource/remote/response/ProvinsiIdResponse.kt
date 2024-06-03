package com.tricakrawala.batikpedia.data.resource.remote.response

import com.google.gson.annotations.SerializedName

data class ProvinsiIdResponse(

	@field:SerializedName("values")
	val values: ProvinsiId
)

data class ProvinsiId(

	@field:SerializedName("detailProvinsi")
	val detailProvinsi: String,

	@field:SerializedName("namaProvinsi")
	val namaProvinsi: String,

	@field:SerializedName("idProvinsi")
	val idProvinsi: Int,

	@field:SerializedName("imgProvinsi")
	val imgProvinsi: String
)
