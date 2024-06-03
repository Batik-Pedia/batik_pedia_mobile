package com.tricakrawala.batikpedia.data.resource.remote.retrofit

import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaIdResponse
import com.tricakrawala.restapibatikpedia.data.remote.response.BeritaResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("berita")
    suspend fun getAllBerita() : BeritaResponse

    @GET("berita/{idBerita}")
    suspend fun getDetailBerita(@Path("idBerita") idBerita: Int) : BeritaIdResponse
}