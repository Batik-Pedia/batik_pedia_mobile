package com.tricakrawala.batikpedia.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.tricakrawala.batikpedia.data.pref.UserModel
import com.tricakrawala.batikpedia.domain.repositories.BatikRepository
import kotlinx.coroutines.flow.Flow

class AuthPrefUseCase(private val repository: BatikRepository) {
    suspend fun saveSession(user : UserModel){
        repository.saveSession(user)
    }

    fun getSession() : Flow<UserModel> {
        return repository.getSession()
    }

}