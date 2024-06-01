package com.tricakrawala.batikpedia.di

import com.tricakrawala.batikpedia.data.repositories.BatikRepositoryImpl
import com.tricakrawala.batikpedia.domain.repositories.BatikRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(batikRepositoryImpl: BatikRepositoryImpl) : BatikRepository


}