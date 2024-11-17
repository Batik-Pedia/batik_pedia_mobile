package com.tricakrawala.batikpedia.data.pref

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SessionDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")


class UserPreference @Inject constructor( @SessionDataStore private val dataStore: DataStore<Preferences>) {

    suspend fun saveSession(user : UserModel) {
        dataStore.edit { preferences ->
            preferences[NOT_NEW_USER] = true

        }
    }


    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[NOT_NEW_USER] ?: false,

            )
        }
    }

    companion object {
        private val TAG = "Preference"

        private val NOT_NEW_USER = booleanPreferencesKey("isNotNew")

    }
}