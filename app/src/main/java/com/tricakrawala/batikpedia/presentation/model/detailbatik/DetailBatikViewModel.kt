package com.tricakrawala.batikpedia.presentation.model.detailbatik

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.domain.model.KatalogBatik
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailBatikViewModel @Inject constructor(private val batik : BatikPediaUseCase) : ViewModel() {

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