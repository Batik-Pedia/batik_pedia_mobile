package com.tricakrawala.batikpedia.data.resource.remote.retrofit

import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaIdResponse
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogIdResponse
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogResponse
import com.tricakrawala.restapibatikpedia.data.remote.response.BeritaResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("berita")
    suspend fun getAllBerita() : BeritaResponse

    @GET("berita/{idBerita}")
    suspend fun getDetailBerita(@Path("idBerita") idBerita: Int) : BeritaIdResponse

    @GET("katalog")
    suspend fun getKatalogBatik() : KatalogResponse

    @GET("katalog/{idBatik}")
    suspend fun getDetailBatik(@Path("idBatik")idBatik : Int) : KatalogIdResponse
}