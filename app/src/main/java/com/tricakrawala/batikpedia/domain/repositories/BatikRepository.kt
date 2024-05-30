package com.tricakrawala.batikpedia.domain.repositories

import com.tricakrawala.batikpedia.data.pref.UserModel
import com.tricakrawala.batikpedia.data.resource.local.datamodel.Berita
import com.tricakrawala.batikpedia.data.resource.local.datamodel.KatalogBatik
import com.tricakrawala.batikpedia.data.resource.local.datamodel.KursusBatik
import com.tricakrawala.batikpedia.data.resource.local.datamodel.Nusantara
import com.tricakrawala.batikpedia.data.resource.local.datamodel.Rekomendasi
import com.tricakrawala.batikpedia.data.resource.local.datamodel.VideoMembatik
import com.tricakrawala.batikpedia.data.resource.local.datamodel.Wisata
import kotlinx.coroutines.flow.Flow

interface BatikRepository {
    suspend fun saveSession(user : UserModel)
    fun getSession(): Flow<UserModel>
    fun getAllNusantara(): Flow<List<Nusantara>>
    fun getAllRekomendasi(): Flow<List<Rekomendasi>>
    fun getAllBatik(): Flow<List<KatalogBatik>>
    suspend fun getBatikById(idBatik : Long) : KatalogBatik
    fun getAllWisata(): Flow<List<Wisata>>
    suspend fun getWisataById(idWisata : Long) : Wisata
    fun getAllBerita() : Flow<List<Berita>>
    suspend fun getBeritaById(idBerita: Long) : Berita
    suspend fun getProvinsiById(idProvinsi : Long) : Nusantara
    fun getAllKursus(): Flow<List<KursusBatik>>
    suspend fun getKursusById(idKursus : Long) : KursusBatik
    fun getAllVideo(): Flow<List<VideoMembatik>>
}