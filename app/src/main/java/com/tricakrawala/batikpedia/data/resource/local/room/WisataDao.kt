package com.tricakrawala.batikpedia.data.resource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataId

@Dao
interface WisataDao {
    @Insert
    suspend fun insertFavoriteWisata(wisata: WisataId)

    @Query("SELECT * FROM wisata_batik_favorite")
    fun getAllWisataBatikFavorite(): List<WisataId>

    @Query("DELETE FROM wisata_batik_favorite WHERE idWisata = :idWisata")
    suspend fun deleteFavorite(idWisata : Int)
}