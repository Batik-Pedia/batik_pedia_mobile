package com.tricakrawala.batikpedia.presentation.model.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.repositories.BatikRepositoryImpl
import com.tricakrawala.batikpedia.data.resource.local.datamodel.Nusantara
import com.tricakrawala.batikpedia.data.resource.local.datamodel.Rekomendasi
import com.tricakrawala.batikpedia.domain.usecase.NusantaraUseCase
import com.tricakrawala.batikpedia.domain.usecase.RecomendUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val nusantara : NusantaraUseCase, private val rekomendasi: RecomendUseCase) : ViewModel() {

    private val _uiStateNusantara: MutableStateFlow<UiState<List<Nusantara>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateNusantara: StateFlow<UiState<List<Nusantara>>> get() = _uiStateNusantara


    private val _uiStateRekomendasi: MutableStateFlow<UiState<List<Rekomendasi>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateRekomendasi: StateFlow<UiState<List<Rekomendasi>>> get() = _uiStateRekomendasi

    fun getAllNusantara() {
        viewModelScope.launch {
            nusantara.getAllNusantara()
                .catch {
                    _uiStateNusantara.value = UiState.Error(it.message.toString())
                }
                .collect { nusantara ->
                    _uiStateNusantara.value = UiState.Success(nusantara)
                }
        }
    }


    fun getAllRekomendasi() {
        viewModelScope.launch {
            rekomendasi.getAllRekomendasi()
                .catch {
                    _uiStateRekomendasi.value = UiState.Error(it.message.toString())
                }
                .collect { rekomendasi ->
                    _uiStateRekomendasi.value = UiState.Success(rekomendasi)
                }
        }
    }


}