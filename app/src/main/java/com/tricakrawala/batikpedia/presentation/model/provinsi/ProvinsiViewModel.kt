package com.tricakrawala.batikpedia.presentation.model.provinsi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogBatikItem
import com.tricakrawala.batikpedia.domain.model.KatalogBatik
import com.tricakrawala.batikpedia.domain.model.Nusantara
import com.tricakrawala.batikpedia.domain.model.Wisata
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProvinsiViewModel @Inject constructor(private val useCase : BatikPediaUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Nusantara>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Nusantara>>> get() = _uiState

    private val _uiStateDetail: MutableStateFlow<UiState<Nusantara>> =
        MutableStateFlow(UiState.Loading)
    val uiStateDetail: StateFlow<UiState<Nusantara>> get() = _uiStateDetail

    private val _uiStateBatik: MutableStateFlow<UiState<List<KatalogBatikItem>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateBatik: StateFlow<UiState<List<KatalogBatikItem>>> get() = _uiStateBatik

    private val _uiStateWisata: MutableStateFlow<UiState<List<Wisata>>> = MutableStateFlow(
        UiState.Loading)
    val uiStateWisata: StateFlow<UiState<List<Wisata>>> get() = _uiStateWisata


    private val _uiStateWisataById: MutableStateFlow<UiState<Wisata>> = MutableStateFlow(
        UiState.Loading)
    val uiStateWisataById: StateFlow<UiState<Wisata>> get() = _uiStateWisataById


    fun getAllNusantara() {
        viewModelScope.launch {
            useCase.getAllNusantara()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { nusantara ->
                    _uiState.value = UiState.Success(nusantara)
                }
        }
    }

    fun getProvinsiById(idProvinsi : Long){
        viewModelScope.launch {
            _uiStateDetail.value = UiState.Loading
            _uiStateDetail.value = UiState.Success(useCase.getProvinsiById(idProvinsi))
        }
    }

    fun getAllBatik() {
        viewModelScope.launch {
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
        viewModelScope.launch {
            useCase.getAllWisata()
                .catch {
                    _uiStateWisata.value = UiState.Error(it.message.toString())
                }
                .collect { wisata ->
                    _uiStateWisata.value = UiState.Success(wisata)
                }
        }
    }

    fun getWisataById(idWisata : Long){
        viewModelScope.launch {
            _uiStateWisataById.value = UiState.Loading
            _uiStateWisataById.value = UiState.Success(useCase.getWisataById(idWisata))
        }
    }

}