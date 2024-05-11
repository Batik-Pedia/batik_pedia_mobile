package com.tricakrawala.batikpedia.screen.provinsi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.BatikRepository
import com.tricakrawala.batikpedia.model.KatalogBatik
import com.tricakrawala.batikpedia.model.Nusantara
import com.tricakrawala.batikpedia.model.Wisata
import com.tricakrawala.batikpedia.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProvinsiViewModel(private val repository: BatikRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Nusantara>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Nusantara>>> get() = _uiState

    private val _uiStateDetail: MutableStateFlow<UiState<Nusantara>> =
        MutableStateFlow(UiState.Loading)
    val uiStateDetail: StateFlow<UiState<Nusantara>> get() = _uiStateDetail

    private val _uiStateBatik: MutableStateFlow<UiState<List<KatalogBatik>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateBatik: StateFlow<UiState<List<KatalogBatik>>> get() = _uiStateBatik

    private val _uiStateWisata: MutableStateFlow<UiState<List<Wisata>>> = MutableStateFlow(UiState.Loading)
    val uiStateWisata: StateFlow<UiState<List<Wisata>>> get() = _uiStateWisata


    private val _uiStateWisataById: MutableStateFlow<UiState<Wisata>> = MutableStateFlow(UiState.Loading)
    val uiStateWisataById: StateFlow<UiState<Wisata>> get() = _uiStateWisataById


    fun getAllNusantara() {
        viewModelScope.launch {
            repository.getAllNusantara()
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
            _uiStateDetail.value = UiState.Success(repository.getProvinsiById(idProvinsi))
        }
    }

    fun getAllBatik() {
        viewModelScope.launch {
            repository.getAllBatik()
                .catch {
                    _uiStateBatik.value = UiState.Error(it.message.toString())
                }
                .collect { batik ->
                    _uiStateBatik.value = UiState.Success(batik)
                }
        }
    }

    fun getAllWisata() {
        viewModelScope.launch {
            repository.getAllWisata()
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
            _uiStateWisataById.value = UiState.Success(repository.getWisataById(idWisata))
        }
    }

}