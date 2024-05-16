package com.tricakrawala.batikpedia.screen.detailedukasi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.BatikRepository
import com.tricakrawala.batikpedia.model.KursusBatik
import com.tricakrawala.batikpedia.ui.common.UiState
import com.tricakrawala.batikpedia.ui.common.UiState.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailKursusViewModel(private val repository: BatikRepository) : ViewModel() {

    val _uiStateKursus: MutableStateFlow<UiState<KursusBatik>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<KursusBatik>> get() = _uiStateKursus

    fun getAllKursusDetail(idKursus : Long) {
        viewModelScope.launch {
            _uiStateKursus.value = UiState.Loading
            _uiStateKursus.value = Success(repository.getKursusById(idKursus))
        }
    }
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

}

