package com.tricakrawala.batikpedia.di

import android.content.Context
import androidx.room.Room
import com.tricakrawala.batikpedia.data.resource.local.room.WisataDao
import com.tricakrawala.batikpedia.data.resource.local.room.WisataDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): WisataDatabase {
        return Room.databaseBuilder(context, WisataDatabase::class.java, "wisata_batik_favorite")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCocktailDao(database: WisataDatabase): WisataDao {
        return database.wisataDao()
    }
}