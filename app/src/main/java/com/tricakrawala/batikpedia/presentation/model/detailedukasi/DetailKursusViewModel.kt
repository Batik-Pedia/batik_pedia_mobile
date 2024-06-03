package com.tricakrawala.batikpedia.presentation.model.detailedukasi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.resource.remote.response.KursusId
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailKursusViewModel @Inject constructor(private val course : BatikPediaUseCase) : ViewModel() {

    private val _uiStateKursus: MutableStateFlow<UiState<KursusId>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<KursusId>> get() = _uiStateKursus

    fun getAllKursusDetail(idKursus : Long) {
        viewModelScope.launch {
            course.getKursusById(idKursus.toInt()).catch {
                _uiStateKursus.value = UiState.Error(it.message.toString())
            }.collect {
                _uiStateKursus.value = it
            }

        }
    }
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

}

