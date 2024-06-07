package com.tricakrawala.batikpedia.domain.usecase

import com.tricakrawala.batikpedia.data.pref.FilterState
import com.tricakrawala.batikpedia.data.pref.UserModel
import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaId
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogBatikItem
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogId
import com.tricakrawala.batikpedia.data.resource.remote.response.KursusItem
import com.tricakrawala.batikpedia.data.resource.remote.response.ProvinsiId
import com.tricakrawala.batikpedia.data.resource.remote.response.ProvinsiItem
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataId
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataItem
import com.tricakrawala.batikpedia.domain.model.Rekomendasi
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaItem
import com.tricakrawala.batikpedia.data.resource.remote.response.KursusId
import com.tricakrawala.batikpedia.data.resource.remote.response.ValuesItem
import kotlinx.coroutines.flow.Flow

interface BatikPediaUseCase {
    suspend fun saveSession(user : UserModel)
    fun getSession(): Flow<UserModel>
    fun getAllNusantara(): Flow<UiState<List<ProvinsiItem>>>
    fun getProvinsiById(idProvinsi : Int) : Flow<UiState<ProvinsiId>>
    fun getAllRekomendasi(): Flow<List<Rekomendasi>>
    fun getAllBatik(
        order: String? = null,
        filterWilayah: String? = null,
        filterJenisBatik: String? = null
    ): Flow<UiState<List<KatalogBatikItem>>>
    fun getBatikById(idBatik : Int) : Flow<UiState<KatalogId>>
    fun getAllWisata(): Flow<UiState<List<WisataItem>>>
    fun getWisataById(idWisata : Int) : Flow<UiState<WisataId>>
    fun getAllBerita() : Flow<UiState<List<BeritaItem>>>
    fun getBeritaById(idBerita : Int) : Flow<UiState<BeritaId>>
    fun getAllKursus(): Flow<UiState<List<KursusItem>>>
    fun getKursusById(idKursus : Int) : Flow<UiState<KursusId>>
    fun getAllVideo(): Flow<UiState<List<ValuesItem>>>
    suspend fun saveFilter(filterState: FilterState)
    fun getFilter(): Flow<UiState<FilterState>>
}