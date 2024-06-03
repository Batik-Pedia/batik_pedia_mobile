package com.tricakrawala.batikpedia.presentation.model.katalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogBatikItem
import com.tricakrawala.batikpedia.domain.model.KatalogBatik
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KatalogViewModel @Inject constructor(private val useCase : BatikPediaUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<KatalogBatikItem>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<KatalogBatikItem>>> get() = _uiState

    fun getAllBatik() {
        viewModelScope.launch {
            useCase.getAllBatik()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = it
                }
        }
    }

}