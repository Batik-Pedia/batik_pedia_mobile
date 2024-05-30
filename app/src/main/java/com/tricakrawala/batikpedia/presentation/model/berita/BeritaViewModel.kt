package com.tricakrawala.batikpedia.presentation.model.berita

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.resource.local.datamodel.Berita
import com.tricakrawala.batikpedia.domain.usecase.BeritaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class BeritaViewModel(private val berita : BeritaUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<com.tricakrawala.batikpedia.presentation.ui.common.UiState<List<Berita>>> =
        MutableStateFlow(com.tricakrawala.batikpedia.presentation.ui.common.UiState.Loading)
    val uiState: StateFlow<com.tricakrawala.batikpedia.presentation.ui.common.UiState<List<Berita>>> get() = _uiState

    private val _uiStateDetail: MutableStateFlow<com.tricakrawala.batikpedia.presentation.ui.common.UiState<Berita>> =
        MutableStateFlow(com.tricakrawala.batikpedia.presentation.ui.common.UiState.Loading)
    val uiStateDetail: StateFlow<com.tricakrawala.batikpedia.presentation.ui.common.UiState<Berita>> get() = _uiStateDetail

    fun getAllBerita() {
        viewModelScope.launch {
            berita.getAllBerita()
                .catch {
                    _uiState.value = com.tricakrawala.batikpedia.presentation.ui.common.UiState.Error(it.message.toString())
                }
                .collect { berita ->
                    _uiState.value = com.tricakrawala.batikpedia.presentation.ui.common.UiState.Success(berita)
                }
        }
    }

    fun getDetailBerita(idBerita : Long) {
        viewModelScope.launch {
            val data = berita.getBeritaById(idBerita)
            _uiStateDetail.value = com.tricakrawala.batikpedia.presentation.ui.common.UiState.Loading
            _uiStateDetail.value = com.tricakrawala.batikpedia.presentation.ui.common.UiState.Success(data)
        }
    }

}