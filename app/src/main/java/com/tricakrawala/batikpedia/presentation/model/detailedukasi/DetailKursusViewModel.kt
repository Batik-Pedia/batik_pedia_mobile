package com.tricakrawala.batikpedia.presentation.model.detailedukasi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.resource.local.datamodel.KursusBatik
import com.tricakrawala.batikpedia.domain.usecase.CourseUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.presentation.ui.common.UiState.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailKursusViewModel(private val course : CourseUseCase) : ViewModel() {

    val _uiStateKursus: MutableStateFlow<UiState<KursusBatik>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<KursusBatik>> get() = _uiStateKursus

    fun getAllKursusDetail(idKursus : Long) {
        viewModelScope.launch {
            _uiStateKursus.value = UiState.Loading
            _uiStateKursus.value = Success(course.getKursusById(idKursus))
        }
    }
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

}

