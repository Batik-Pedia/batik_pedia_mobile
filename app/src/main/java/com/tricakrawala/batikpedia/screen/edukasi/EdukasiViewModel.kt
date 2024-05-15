package com.tricakrawala.batikpedia.screen.edukasi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.BatikRepository
import com.tricakrawala.batikpedia.model.KursusBatik
import com.tricakrawala.batikpedia.model.VideoMembatik
import com.tricakrawala.batikpedia.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class EdukasiViewModel(private val repository: BatikRepository) : ViewModel() {

    private val _uiStateKursus: MutableStateFlow<UiState<List<KursusBatik>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateKursus: StateFlow<UiState<List<KursusBatik>>> get() = _uiStateKursus

    private val _uiStateVideoMembatik: MutableStateFlow<UiState<List<VideoMembatik>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateVideoMembatik: StateFlow<UiState<List<VideoMembatik>>> get() = _uiStateVideoMembatik

    fun getAllKursus() {
        viewModelScope.launch {
            repository.getAllKursus()
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
            repository.getAllVideo()
                .catch {
                    _uiStateVideoMembatik.value = UiState.Error(it.message.toString())
                }
                .collect { video ->
                    _uiStateVideoMembatik.value = UiState.Success(video)
                }
        }
    }

}