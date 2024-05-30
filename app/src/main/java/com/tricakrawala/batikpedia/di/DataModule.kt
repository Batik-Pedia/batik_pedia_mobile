package com.tricakrawala.batikpedia.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.tricakrawala.batikpedia.presentation.model.main.MainViewModel
import com.tricakrawala.batikpedia.data.repositories.BatikRepositoryImpl
import com.tricakrawala.batikpedia.data.pref.UserPreference
import com.tricakrawala.batikpedia.data.pref.dataStore
import com.tricakrawala.batikpedia.domain.repositories.BatikRepository
import com.tricakrawala.batikpedia.domain.usecase.AuthPrefUseCase
import com.tricakrawala.batikpedia.domain.usecase.BatikUseCase
import com.tricakrawala.batikpedia.domain.usecase.BeritaUseCase
import com.tricakrawala.batikpedia.domain.usecase.CourseUseCase
import com.tricakrawala.batikpedia.domain.usecase.NusantaraUseCase
import com.tricakrawala.batikpedia.domain.usecase.RecomendUseCase
import com.tricakrawala.batikpedia.domain.usecase.WisataUseCase
import com.tricakrawala.batikpedia.presentation.model.berita.BeritaViewModel
import com.tricakrawala.batikpedia.presentation.model.detailbatik.DetailBatikViewModel
import com.tricakrawala.batikpedia.presentation.model.detailedukasi.DetailKursusViewModel
import com.tricakrawala.batikpedia.presentation.model.edukasi.EdukasiViewModel
import com.tricakrawala.batikpedia.presentation.model.home.HomeViewModel
import com.tricakrawala.batikpedia.presentation.model.katalog.KatalogViewModel
import com.tricakrawala.batikpedia.presentation.model.provinsi.ProvinsiViewModel
import com.tricakrawala.batikpedia.presentation.model.wisata.WisataViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val dataModule = module {
    single { provideDataStore(androidContext()) }
    single<BatikRepository> {  BatikRepositoryImpl(get())}
    single { UserPreference(get()) }

    single {AuthPrefUseCase(get())}
    single {BatikUseCase(get())}
    single {BeritaUseCase(get())}
    single {CourseUseCase(get())}
    single {NusantaraUseCase(get())}
    single {WisataUseCase(get())}
    single { RecomendUseCase(get()) }

    viewModel { MainViewModel(get()) }
    viewModel { HomeViewModel(get(),get()) }
    viewModel { KatalogViewModel(get()) }
    viewModel { ProvinsiViewModel(get(),get(),get()) }
    viewModel { WisataViewModel(get()) }
    viewModel { BeritaViewModel(get()) }
    viewModel { DetailBatikViewModel(get()) }
    viewModel { EdukasiViewModel(get()) }
    viewModel { DetailKursusViewModel(get()) }
}

fun provideDataStore(context: Context): DataStore<Preferences> {
    return context.dataStore
}