package com.tricakrawala.batikpedia.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FilterDataStore

val Context.filterStore: DataStore<Preferences> by preferencesDataStore(name = "filter")

class FilterPreference @Inject constructor(@SessionDataStore private val filterStore: DataStore<Preferences>) {

    suspend fun saveFilter(filter : FilterState) {
        filterStore.edit { preferences ->
            preferences[SORT] = filter.sort
            preferences[WILAYAH] = filter.wilayah
            preferences[JENIS_BATIK] = filter.jenisBatik

        }
    }


    fun getFilter(): Flow<FilterState> {
        return filterStore.data.map { preferences ->
           FilterState(
               preferences[SORT] ?: "asc",
               preferences[WILAYAH] ?: "Semua",
               preferences[JENIS_BATIK] ?: "Semua"

           )
        }
    }

    companion object {


        private val SORT = stringPreferencesKey("sort")
        private val WILAYAH = stringPreferencesKey("wilayah")
        private val JENIS_BATIK = stringPreferencesKey("jenis batik")

    }

}