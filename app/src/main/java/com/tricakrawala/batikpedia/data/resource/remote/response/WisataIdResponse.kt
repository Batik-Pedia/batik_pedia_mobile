package com.tricakrawala.batikpedia.data.resource.remote.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class WisataIdResponse(

	@field:SerializedName("values")
	val values: WisataId
)

@Entity(tableName = "wisata_batik_favorite")
data class WisataId(

	@PrimaryKey
	@field:SerializedName("idWisata")
	val idWisata: Int,

	@field:SerializedName("namaWisata")
	val namaWisata: String,

	@field:SerializedName("imageWisata")
	val imageWisata: String,

	@field:SerializedName("lon")
	val lon: Double,

	@field:SerializedName("detailWisata")
	val detailWisata: String,

	@field:SerializedName("lat")
	val lat: Double,

	@field:SerializedName("wilayah")
	val wilayah: String,

	@ColumnInfo(name = "isFavorite")
	val isFavorite : Boolean
)
