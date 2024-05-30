package com.tricakrawala.batikpedia.presentation.model.katalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.repositories.BatikRepositoryImpl
import com.tricakrawala.batikpedia.data.resource.local.datamodel.KatalogBatik
import com.tricakrawala.batikpedia.domain.usecase.BatikUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class KatalogViewModel(private val katalog : BatikUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<KatalogBatik>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<KatalogBatik>>> get() = _uiState

    fun getAllBatik() {
        viewModelScope.launch {
            katalog.getAllBatik()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { batik ->
                    _uiState.value = UiState.Success(batik)
                }
        }
    }

}