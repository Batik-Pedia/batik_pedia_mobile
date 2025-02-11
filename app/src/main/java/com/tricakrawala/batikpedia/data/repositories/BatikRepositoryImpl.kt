package com.tricakrawala.batikpedia.data.repositories

import com.tricakrawala.batikpedia.data.pref.FilterPreference
import com.tricakrawala.batikpedia.data.pref.FilterState
import com.tricakrawala.batikpedia.data.pref.UserModel
import com.tricakrawala.batikpedia.data.pref.UserPreference
import com.tricakrawala.batikpedia.data.resource.local.LocalDataSource
import com.tricakrawala.batikpedia.data.resource.remote.RemoteDataSource
import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaId
import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaItem
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogBatikItem
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogId
import com.tricakrawala.batikpedia.data.resource.remote.response.KursusId
import com.tricakrawala.batikpedia.data.resource.remote.response.KursusItem
import com.tricakrawala.batikpedia.data.resource.remote.response.ProvinsiId
import com.tricakrawala.batikpedia.data.resource.remote.response.ProvinsiItem
import com.tricakrawala.batikpedia.data.resource.remote.response.RekomendasiItem
import com.tricakrawala.batikpedia.data.resource.remote.response.ValuesItem
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataId
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataItem
import com.tricakrawala.batikpedia.domain.repositories.BatikRepository
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BatikRepositoryImpl @Inject constructor(
    private val preference: UserPreference,
    private val filterPref : FilterPreference,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : BatikRepository {


    override suspend fun saveSession(user: UserModel) {
        preference.saveSession(user)
    }

    override fun getSession(): Flow<UserModel> {
        return preference.getSession()
    }

    override fun getAllNusantara(): Flow<UiState<List<ProvinsiItem>>> =
        flow {
            emit(UiState.Loading)
            try {
                val response = remoteDataSource.getAllProvinsi()
                val result = response.values
                emit(UiState.Success(result))
            } catch (e: Exception) {
                emit(UiState.Error(e.message ?: "Unknown Error"))
            }
        }.flowOn(Dispatchers.IO)


    override fun getAllBatik(
        order: String?,
        filterWilayah: String?,
        filterJenisBatik: String?
    ): Flow<UiState<List<KatalogBatikItem>>> = flow {
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getAllBatik(order = order, filterWilayah = filterWilayah, filterJenisBatik = filterJenisBatik)
            val result = response.values.katalogBatik
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getBatikById(idBatik: Int): Flow<UiState<KatalogId>> = flow {
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getDetailBatik(idBatik)
            val result = response.values
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAllWisata(): Flow<UiState<List<WisataItem>>> = flow {
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getAllWisata()
            val result = response.values.wisata
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getWisataById(idWisata: Int): Flow<UiState<WisataId>> = flow {
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getDetailWisata(idWisata)
            val result = response.values
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }

    }.flowOn(Dispatchers.IO)

    override fun getAllBerita(): Flow<UiState<List<BeritaItem>>> = flow {
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getAllBerita()
            val result = response.values.berita
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getBeritaById(idBerita: Int): Flow<UiState<BeritaId>> = flow {
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getDetailBerita(idBerita)
            val result = response.values
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)


    override fun getProvinsiById(idProvinsi: Int): Flow<UiState<ProvinsiId>> = flow {
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getProvinsiId(idProvinsi)
            val result = response.values
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAllRekomendasi(): Flow<UiState<List<RekomendasiItem>>> = flow{
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getAllRekomendasi()
            val result = response.values.rekomendasi
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAllKursus(): Flow<UiState<List<KursusItem>>> = flow {
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getAllKursus()
            val result = response.values.kursus
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }

    }.flowOn(Dispatchers.IO)

    override fun getKursusById(idKursus: Int): Flow<UiState<KursusId>> = flow {
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getDetailKursus(idKursus)
            val result = response.values
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAllVideo(): Flow<UiState<List<ValuesItem>>> = flow {
        emit(UiState.Loading)
        try {
            val response = remoteDataSource.getAllVideo()
            val result = response.values
            emit(UiState.Success(result))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }

    }.flowOn(Dispatchers.IO)

    override suspend fun saveFilter(filterState: FilterState) {
        filterPref.saveFilter(filterState)
    }

    override fun getFilter(): Flow<UiState<FilterState>> = flow {
        emit(UiState.Loading)
        try {
            val response = filterPref.getFilter().first()
            emit(UiState.Success(response))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }
    }

    override suspend fun insertFavoriteWisata(wisata: WisataId) =
        localDataSource.insertFavoriteWisata(wisata)

    override fun getAllWisataBatikFavorite(): Flow<UiState<List<WisataId>>> = flow {
        emit(UiState.Loading)
        try {
            val room = localDataSource.getAllWisataBatikFavorite()
            emit(UiState.Success(room))
        } catch (e: Exception) {
            emit(UiState.Error(e.message ?: "Unknown Error"))
        }
    }

    override suspend fun deleteFavorite(idWisata: Int) = localDataSource.deleteFavoriteWisata(idWisata)

}