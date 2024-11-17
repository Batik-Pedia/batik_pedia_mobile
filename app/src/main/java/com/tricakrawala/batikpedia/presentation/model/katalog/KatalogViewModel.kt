package com.tricakrawala.batikpedia.presentation.model.katalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.pref.FilterState
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogBatikItem
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KatalogViewModel @Inject constructor(private val useCase: BatikPediaUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<KatalogBatikItem>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<KatalogBatikItem>>> get() = _uiState

    private val _filterUiState: MutableStateFlow<UiState<FilterState>> =
        MutableStateFlow(UiState.Loading)
    val filterUiState: StateFlow<UiState<FilterState>> get() = _filterUiState

    private val _saveState : MutableStateFlow<UiState<Unit>> = MutableStateFlow(UiState.Loading)
    val saveState : StateFlow<UiState<Unit>> get() = _saveState

    init {
        getFilter()
    }

    fun saveFilter(filter: FilterState) {
        viewModelScope.launch(Dispatchers.IO) {
            _saveState.value = UiState.Loading
            try {
                _saveState.value = UiState.Success(useCase.saveFilter(filter))
            }catch (e : Exception){
                _saveState.value = UiState.Error(e.message.toString())
            }
        }
    }

    private fun getFilter() {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getFilter()
                .catch {
                    _filterUiState.value = UiState.Error(it.message.toString())
                }
                .collect { filterUiState ->
                    _filterUiState.value = filterUiState
                    if (filterUiState is UiState.Success) {
                        getAllBatik(filterUiState.data)
                    }

                }
        }
    }

    fun getAllBatik(filter: FilterState) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllBatik(filter.sort, filter.wilayah, filter.jenisBatik)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { batikUiState ->
                    _uiState.value = batikUiState
                }
        }
    }
}

