package com.tricakrawala.batikpedia.data.repositories

import com.tricakrawala.batikpedia.data.pref.UserModel
import com.tricakrawala.batikpedia.data.pref.UserPreference
import com.tricakrawala.batikpedia.data.resource.remote.RemoteDataSource
import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaId
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogBatikItem
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogId
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataId
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataItem
import com.tricakrawala.batikpedia.domain.repositories.BatikRepository
import com.tricakrawala.batikpedia.domain.model.Berita
import com.tricakrawala.batikpedia.domain.model.FakeSourceBatik
import com.tricakrawala.batikpedia.domain.model.KatalogBatik
import com.tricakrawala.batikpedia.domain.model.KursusBatik
import com.tricakrawala.batikpedia.domain.model.Nusantara
import com.tricakrawala.batikpedia.domain.model.Rekomendasi
import com.tricakrawala.batikpedia.domain.model.VideoMembatik
import com.tricakrawala.batikpedia.domain.model.Wisata
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.restapibatikpedia.data.remote.response.BeritaItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BatikRepositoryImpl @Inject constructor(private val preference: UserPreference, private val remoteDataSource: RemoteDataSource) : BatikRepository {

    private val nusantaraList = mutableListOf<Nusantara>()
    private val rekomendasiList = mutableListOf<Rekomendasi>()
    private val batikList = mutableListOf<KatalogBatik>()
    private val wisataList = mutableListOf<Wisata>()
    private val kursusList = mutableListOf<KursusBatik>()
    private val videoList = mutableListOf<VideoMembatik>()

    init{
        if (nusantaraList.isEmpty()){
            FakeSourceBatik.listNusantara.forEach {
                nusantaraList.add(it)
            }
        }

        if (rekomendasiList.isEmpty()){
            FakeSourceBatik.listRekomendasi.forEach {
                rekomendasiList.add(it)
            }
        }

        if (batikList.isEmpty()){
            FakeSourceBatik.listBatik.forEach {
                batikList.add(it)
            }
        }

        if (wisataList.isEmpty()){
            FakeSourceBatik.listWisata.forEach {
                wisataList.add(it)
            }
        }

        if (kursusList.isEmpty()){
            FakeSourceBatik.listKursus.forEach {
                kursusList.add(it)
            }
        }
        if (videoList.isEmpty()){
            FakeSourceBatik.listVideoMembatik.forEach {
                videoList.add(it)
            }
        }
    }

    override suspend fun saveSession(user: UserModel) {
        preference.saveSession()
    }

    override fun getSession(): Flow<UserModel> {
        return preference.getSession()
    }

    override fun getAllNusantara(): Flow<List<Nusantara>> {
        return flowOf(nusantaraList)
    }

    override fun getAllRekomendasi(): Flow<List<Rekomendasi>> {
        return flowOf(rekomendasiList)
    }

    override fun getAllBatik(): Flow<UiState<List<KatalogBatikItem>>> = flow {
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getAllBatik()
            val result = response.values.katalogBatik
            emit(UiState.Success(result))
        }catch (e : Exception){
            emit(UiState.Error(e.message ?:"Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getBatikById(idBatik: Int): Flow<UiState<KatalogId>> = flow{
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getDetailBatik(idBatik)
            val result = response.values
            emit(UiState.Success(result))
        }catch (e : Exception){
            emit(UiState.Error(e.message ?:"Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAllWisata(): Flow<UiState<List<WisataItem>>> = flow{
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getAllWisata()
            val result = response.values.wisata
            emit(UiState.Success(result))
        }catch (e : Exception){
            emit(UiState.Error(e.message ?:"Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getWisataById(idWisata: Int): Flow<UiState<WisataId>> = flow{
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getDetailWisata(idWisata)
            val result = response.values
            emit(UiState.Success(result))
        }catch (e : Exception){
            emit(UiState.Error(e.message ?:"Unknown Error"))
        }

    }.flowOn(Dispatchers.IO)

    override fun getAllBerita(): Flow<UiState<List<BeritaItem>>> = flow {
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getAllBerita()
            val result = response.values.berita
            emit(UiState.Success(result))
        }catch (e : Exception){
            emit(UiState.Error(e.message ?:"Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getBeritaById(idBerita: Int): Flow<UiState<BeritaId>> = flow {
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getDetailBerita(idBerita)
            val result = response.values
            emit(UiState.Success(result))
        }catch (e : Exception){
            emit(UiState.Error(e.message ?:"Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun getProvinsiById(idProvinsi: Long): Nusantara {
        return nusantaraList.first { it.idNusantara == idProvinsi }
    }

    override fun getAllKursus(): Flow<List<KursusBatik>> {
        return flowOf(kursusList)
    }

    override suspend fun getKursusById(idKursus: Long): KursusBatik {
        return kursusList.first { it.idKursus == idKursus }
    }

    override fun getAllVideo(): Flow<List<VideoMembatik>> {
        return flowOf(videoList)
    }

}