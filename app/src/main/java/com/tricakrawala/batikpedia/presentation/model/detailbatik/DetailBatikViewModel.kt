package com.tricakrawala.batikpedia.presentation.model.detailbatik

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.resource.local.datamodel.KatalogBatik
import com.tricakrawala.batikpedia.domain.usecase.BatikUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailBatikViewModel(private val batik : BatikUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<KatalogBatik>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<KatalogBatik>> get() = _uiState

    fun getAllWisata(idBatik : Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(batik.getBatikById(idBatik))
        }
    }
}