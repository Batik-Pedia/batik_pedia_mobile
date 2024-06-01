package com.tricakrawala.batikpedia.presentation.model.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.pref.UserModel
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val useCase : BatikPediaUseCase): ViewModel(){

    fun getSession(): LiveData<UserModel> {
        return useCase.getSession().asLiveData()
    }

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            useCase.saveSession(user)
        }
    }

}