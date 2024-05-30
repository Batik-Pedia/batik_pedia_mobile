package com.tricakrawala.batikpedia.domain.usecase

import com.tricakrawala.batikpedia.data.resource.local.datamodel.Berita
import com.tricakrawala.batikpedia.domain.repositories.BatikRepository
import kotlinx.coroutines.flow.Flow

class BeritaUseCase(private val repository: BatikRepository) {
    fun getAllBerita() : Flow<List<Berita>>{
        return  repository.getAllBerita()
    }

    suspend fun getBeritaById(idBerita: Long) : Berita{
        return  repository.getBeritaById(idBerita)
    }
}