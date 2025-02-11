package com.tricakrawala.batikpedia.presentation.model.wisatafavorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataId
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WisataFavoriteViewModel @Inject constructor(private val useCase: BatikPediaUseCase) : ViewModel() {
    private val _listFavorite : MutableStateFlow<UiState<List<WisataId>>> = MutableStateFlow(
        UiState.Loading)
    val listFavorite: StateFlow<UiState<List<WisataId>>> get() = _listFavorite

    private val _insertState : MutableStateFlow<UiState<Unit>> = MutableStateFlow(UiState.Loading)
    val insertState: MutableStateFlow<UiState<Unit>> get() = _insertState

    private val _deleteState : MutableStateFlow<UiState<Unit>> = MutableStateFlow(UiState.Loading)
    val deleteState: MutableStateFlow<UiState<Unit>> get() = _deleteState

    init {
        getAllWisataBatikFavorite()
    }

    fun getAllWisataBatikFavorite(){
        viewModelScope.launch(Dispatchers.IO) {
            useCase.getAllWisataBatikFavorite().collect{
                _listFavorite.value = it
            }
        }
    }

    fun insertFavoriteWisata(wisataBatik: WisataId){
        viewModelScope.launch(Dispatchers.IO) {
            _insertState.value = UiState.Loading

            try {
                _insertState.value = UiState.Success(useCase.insertFavoriteWisata(wisataBatik))
            }catch (e : Exception){
                _insertState.value = UiState.Error(e.message.toString())
            }
        }

    }

    fun deleteFavorite(idWisata :Int){
        viewModelScope.launch(Dispatchers.IO) {
            _deleteState.value = UiState.Loading
            try {
                _deleteState.value = UiState.Success(useCase.deleteFavorite(idWisata))
            }catch (e : Exception){
                _deleteState.value = UiState.Error(e.message.toString())
            }
        }
    }
}