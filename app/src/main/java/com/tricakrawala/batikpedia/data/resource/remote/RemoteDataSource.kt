package com.tricakrawala.batikpedia.data.resource.remote

import com.tricakrawala.batikpedia.data.resource.remote.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllBerita() = apiService.getAllBerita()
    suspend fun getDetailBerita(idBerita : Int) = apiService.getDetailBerita(idBerita)
    suspend fun getAllBatik(
        order: String?,
        filterWilayah: String?,
        filterJenisBatik: String?
    ) = apiService.getKatalogBatik(order = order, filterWilayah = filterWilayah, filterJenisBatik = filterJenisBatik)
    suspend fun getDetailBatik(idBatik : Int) = apiService.getDetailBatik(idBatik)
    suspend fun getAllWisata() = apiService.getAllWisata()
    suspend fun getDetailWisata(idWisata : Int) = apiService.getDetailWisata(idWisata)
    suspend fun getAllProvinsi() = apiService.getAllProvinsi()
    suspend fun getProvinsiId(idProvinsi : Int) = apiService.getDetailProvinsi(idProvinsi)
    suspend fun getAllKursus() = apiService.getAllKursus()
    suspend fun getDetailKursus(idKursus : Int) = apiService.getDetailKursus(idKursus)
    suspend fun getAllVideo() = apiService.getAllVideo()
    suspend fun getAllRekomendasi() = apiService.getAllRekomendasi()

}