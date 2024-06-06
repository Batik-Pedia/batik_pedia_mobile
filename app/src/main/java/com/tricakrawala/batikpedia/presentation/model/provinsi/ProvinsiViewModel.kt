package com.tricakrawala.batikpedia.presentation.model.provinsi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogBatikItem
import com.tricakrawala.batikpedia.data.resource.remote.response.ProvinsiId
import com.tricakrawala.batikpedia.data.resource.remote.response.ProvinsiItem
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataId
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataItem
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProvinsiViewModel @Inject constructor(private val useCase : BatikPediaUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<ProvinsiItem>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<ProvinsiItem>>> get() = _uiState

    private val _uiStateDetail: MutableStateFlow<UiState<ProvinsiId>> =
        MutableStateFlow(UiState.Loading)
    val uiStateDetail: StateFlow<UiState<ProvinsiId>> get() = _uiStateDetail

    private val _uiStateBatik: MutableStateFlow<UiState<List<KatalogBatikItem>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateBatik: StateFlow<UiState<List<KatalogBatikItem>>> get() = _uiStateBatik

    private val _uiStateWisata: MutableStateFlow<UiState<List<WisataItem>>> = MutableStateFlow(
        UiState.Loading)
    val uiStateWisata: StateFlow<UiState<List<WisataItem>>> get() = _uiStateWisata


    private val _uiStateWisataById: MutableStateFlow<UiState<WisataId>> = MutableStateFlow(
        UiState.Loading)
    val uiStateWisataById: StateFlow<UiState<WisataId>> get() = _uiStateWisataById

init {
    getAllNusantara()
    getAllWisata()
    getAllBatik()
}
    fun getAllNusantara() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllNusantara()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { nusantara ->
                    _uiState.value = nusantara
                }
        }
    }

    fun getProvinsiById(idProvinsi : Long){
        viewModelScope.launch {
            useCase.getProvinsiById(idProvinsi.toInt())
                .catch {
                    _uiStateDetail.value = UiState.Error(it.message.toString())
                }
                .collect { nusantara ->
                    _uiStateDetail.value = nusantara
                }
        }
    }

    fun getAllBatik() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllBatik()
                .catch {
                    _uiStateBatik.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiStateBatik.value = it
                }
        }
    }

    fun getAllWisata() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllWisata()
                .catch {
                    _uiStateWisata.value = UiState.Error(it.message.toString())
                }
                .collect { wisata ->
                    _uiStateWisata.value = wisata
                }
        }
    }

    fun getWisataById(idWisata : Long){
        viewModelScope.launch {
            useCase.getWisataById(idWisata.toInt())
                .catch {
                    _uiStateWisataById.value = UiState.Error(it.message.toString())
                }
                .collect { wisata ->
                    _uiStateWisataById.value = wisata
                }
        }
    }

}