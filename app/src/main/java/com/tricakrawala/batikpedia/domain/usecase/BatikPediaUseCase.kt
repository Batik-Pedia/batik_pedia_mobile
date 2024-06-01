package com.tricakrawala.batikpedia.domain.usecase

import com.tricakrawala.batikpedia.data.pref.UserModel
import com.tricakrawala.batikpedia.domain.model.Berita
import com.tricakrawala.batikpedia.domain.model.KatalogBatik
import com.tricakrawala.batikpedia.domain.model.KursusBatik
import com.tricakrawala.batikpedia.domain.model.Nusantara
import com.tricakrawala.batikpedia.domain.model.Rekomendasi
import com.tricakrawala.batikpedia.domain.model.VideoMembatik
import com.tricakrawala.batikpedia.domain.model.Wisata
import kotlinx.coroutines.flow.Flow

interface BatikPediaUseCase {
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