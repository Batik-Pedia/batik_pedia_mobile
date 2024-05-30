package com.tricakrawala.batikpedia.presentation.model.wisata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.repositories.BatikRepositoryImpl
import com.tricakrawala.batikpedia.data.resource.local.datamodel.Wisata
import com.tricakrawala.batikpedia.domain.usecase.WisataUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WisataViewModel(private val wisata : WisataUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<com.tricakrawala.batikpedia.presentation.ui.common.UiState<List<Wisata>>> =
        MutableStateFlow(com.tricakrawala.batikpedia.presentation.ui.common.UiState.Loading)
    val uiState: StateFlow<com.tricakrawala.batikpedia.presentation.ui.common.UiState<List<Wisata>>> get() = _uiState

    private val _uiStateWisataById: MutableStateFlow<com.tricakrawala.batikpedia.presentation.ui.common.UiState<Wisata>> = MutableStateFlow(
        com.tricakrawala.batikpedia.presentation.ui.common.UiState.Loading)
    val uiStateWisataById: StateFlow<com.tricakrawala.batikpedia.presentation.ui.common.UiState<Wisata>> get() = _uiStateWisataById

    fun getAllWisata() {
        viewModelScope.launch {
            wisata.getAllWisata()
                .catch {
                    _uiState.value = com.tricakrawala.batikpedia.presentation.ui.common.UiState.Error(it.message.toString())
                }
                .collect { wisata ->
                    _uiState.value = com.tricakrawala.batikpedia.presentation.ui.common.UiState.Success(wisata)
                }
        }
    }


    fun getWisataById(idWisata : Long){
        viewModelScope.launch {
            _uiStateWisataById.value = com.tricakrawala.batikpedia.presentation.ui.common.UiState.Loading
            _uiStateWisataById.value = com.tricakrawala.batikpedia.presentation.ui.common.UiState.Success(wisata.getWisataById(idWisata))
        }
    }

}