package com.tricakrawala.batikpedia.domain.usecase

import com.tricakrawala.batikpedia.data.resource.local.datamodel.Rekomendasi
import com.tricakrawala.batikpedia.domain.repositories.BatikRepository
import kotlinx.coroutines.flow.Flow

class RecomendUseCase(private val repository: BatikRepository) {
    fun getAllRekomendasi(): Flow<List<Rekomendasi>> {
        return repository.getAllRekomendasi()
    }
}