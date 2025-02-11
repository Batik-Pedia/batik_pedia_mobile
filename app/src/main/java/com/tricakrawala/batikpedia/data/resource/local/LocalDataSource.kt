package com.tricakrawala.batikpedia.data.resource.local

import com.tricakrawala.batikpedia.data.resource.local.room.WisataDao
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val wisataDao: WisataDao) {

    suspend fun insertFavoriteWisata(wisataBatik: WisataId) = wisataDao.insertFavoriteWisata(wisataBatik)

    fun getAllWisataBatikFavorite() = wisataDao.getAllWisataBatikFavorite()

    suspend fun deleteFavoriteWisata(idWisata : Int) = wisataDao.deleteFavorite(idWisata)
}