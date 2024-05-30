package com.tricakrawala.batikpedia.domain.usecase

import com.tricakrawala.batikpedia.data.resource.local.datamodel.Wisata
import com.tricakrawala.batikpedia.domain.repositories.BatikRepository
import kotlinx.coroutines.flow.Flow

class WisataUseCase(private val repository: BatikRepository) {
    fun getAllWisata(): Flow<List<Wisata>> {
        return repository.getAllWisata()
    }

    suspend fun getWisataById(idWisata : Long) : Wisata {
        return repository.getWisataById(idWisata)
    }

}