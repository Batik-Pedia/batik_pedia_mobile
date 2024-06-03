package com.tricakrawala.batikpedia.presentation.model.edukasi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.resource.remote.response.KursusItem
import com.tricakrawala.batikpedia.domain.model.VideoMembatik
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EdukasiViewModel @Inject constructor(private val edukasi : BatikPediaUseCase) : ViewModel() {

    private val _uiStateKursus: MutableStateFlow<UiState<List<KursusItem>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateKursus: StateFlow<UiState<List<KursusItem>>> get() = _uiStateKursus

    private val _uiStateVideoMembatik: MutableStateFlow<UiState<List<VideoMembatik>>> =
        MutableStateFlow(UiState.Loading)
    val uiStateVideoMembatik: StateFlow<UiState<List<VideoMembatik>>> get() = _uiStateVideoMembatik


    fun getAllKursus() {
        viewModelScope.launch {
            edukasi.getAllKursus()
                .catch {
                    _uiStateKursus.value = UiState.Error(it.message.toString())
                }
                .collect { kursus ->
                    _uiStateKursus.value = kursus
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


}