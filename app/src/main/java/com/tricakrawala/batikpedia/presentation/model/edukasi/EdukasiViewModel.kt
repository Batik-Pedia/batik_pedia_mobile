package com.tricakrawala.batikpedia.presentation.model.edukasi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.resource.local.datamodel.KursusBatik
import com.tricakrawala.batikpedia.data.resource.local.datamodel.VideoMembatik
import com.tricakrawala.batikpedia.domain.usecase.CourseUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class EdukasiViewModel(private val edukasi : CourseUseCase) : ViewModel() {

    private val _uiStateKursus: MutableStateFlow<UiState<List<KursusBatik>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateKursus: StateFlow<UiState<List<KursusBatik>>> get() = _uiStateKursus

    private val _uiStateVideoMembatik: MutableStateFlow<UiState<List<VideoMembatik>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateVideoMembatik: StateFlow<UiState<List<VideoMembatik>>> get() = _uiStateVideoMembatik
      private val _uiStateKursusById : MutableStateFlow<UiState<KursusBatik>> = MutableStateFlow(
          UiState.Loading)

    val uiStateKursusById : StateFlow<UiState<KursusBatik>> get() = _uiStateKursusById

    fun getAllKursus() {
        viewModelScope.launch {
            edukasi.getAllKursus()
                .catch {
                    _uiStateKursus.value = UiState.Error(it.message.toString())
                }
                .collect { kursus ->
                    _uiStateKursus.value = UiState.Success(kursus)
                }
        }
    }
    fun getAllVideo() {
        viewModelScope.launch {
            edukasi.getAllVideo()
                .catch {
                    _uiStateVideoMembatik.value = UiState.Error(it.message.toString())
                }
                .collect { video ->
                    _uiStateVideoMembatik.value = UiState.Success(video)
                }
        }
    }

    fun getKursusById(idKursus : Long) {
        viewModelScope.launch {
            _uiStateKursusById.value = UiState.Loading
            _uiStateKursusById.value = UiState.Success(edukasi.getKursusById(idKursus))
        }
    }

}