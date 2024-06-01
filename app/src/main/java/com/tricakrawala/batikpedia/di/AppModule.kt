package com.tricakrawala.batikpedia.di

import com.tricakrawala.batikpedia.domain.usecase.BatikPediaInteract
import com.tricakrawala.batikpedia.domain.usecase.BatikPediaUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideBatikPediaUseCase(batikPediaInteract: BatikPediaInteract) :BatikPediaUseCase
}