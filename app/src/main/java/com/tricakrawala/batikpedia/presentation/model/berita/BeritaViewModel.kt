package com.tricakrawala.batikpedia.presentation.model.berita

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaId
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeritaViewModel @Inject constructor(private val berita: BatikPediaUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<BeritaItem>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<BeritaItem>>> get() = _uiState

    private val _uiStateDetail = MutableStateFlow<UiState<BeritaId>>(UiState.Loading)
    val uiStateDetail: StateFlow<UiState<BeritaId>> get() = _uiStateDetail

    fun getAllBerita() {
        viewModelScope.launch {
            berita.getAllBerita()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = it
                }
        }
    }

    fun getDetailBerita(idBerita: Int) {
        viewModelScope.launch {
            berita.getBeritaById(idBerita).catch {
                _uiStateDetail.value = UiState.Error(it.message ?: "Unknown error")
            }
                .collect {
                    _uiStateDetail.value = it
                }
        }
    }

}