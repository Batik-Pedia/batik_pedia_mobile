package com.tricakrawala.batikpedia.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.tricakrawala.batikpedia.data.pref.UserPreference
import com.tricakrawala.batikpedia.data.pref.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserPreferenceModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context) : DataStore<Preferences>{
        return context.dataStore
    }

    @Provides
    @Singleton
    fun providePreference(dataStore: DataStore<Preferences>) : UserPreference{
        return UserPreference(dataStore)
    }
}