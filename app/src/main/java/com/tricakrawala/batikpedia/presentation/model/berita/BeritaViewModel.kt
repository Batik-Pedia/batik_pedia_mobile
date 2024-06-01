package com.tricakrawala.batikpedia.presentation.model.berita

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.domain.model.Berita
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeritaViewModel @Inject constructor(private val berita : BatikPediaUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<Berita>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Berita>>> get() = _uiState

    private val _uiStateDetail: MutableStateFlow<UiState<Berita>> =
        MutableStateFlow(UiState.Loading)
    val uiStateDetail: StateFlow<UiState<Berita>> get() = _uiStateDetail

    fun getAllBerita() {
        viewModelScope.launch {
            berita.getAllBerita()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { berita ->
                    _uiState.value = UiState.Success(berita)
                }
        }
    }

    fun getDetailBerita(idBerita : Long) {
        viewModelScope.launch {
            val data = berita.getBeritaById(idBerita)
            _uiStateDetail.value = UiState.Loading
            _uiStateDetail.value = UiState.Success(data)
        }
    }

}