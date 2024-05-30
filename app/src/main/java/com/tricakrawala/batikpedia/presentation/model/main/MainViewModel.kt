package com.tricakrawala.batikpedia.presentation.model.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tricakrawala.batikpedia.data.pref.UserModel
import com.tricakrawala.batikpedia.domain.usecase.AuthPrefUseCase
import kotlinx.coroutines.launch

class MainViewModel(private  val auth : AuthPrefUseCase): ViewModel(){

    fun getSession(): LiveData<UserModel> {
        return auth.getSession().asLiveData()
    }

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            auth.saveSession(user)
        }
    }

}