package com.tricakrawala.batikpedia.domain.usecase

import com.tricakrawala.batikpedia.data.resource.local.datamodel.KatalogBatik
import com.tricakrawala.batikpedia.domain.repositories.BatikRepository
import kotlinx.coroutines.flow.Flow

class BatikUseCase(private val repository: BatikRepository) {
    fun getAllBatik() : Flow<List<KatalogBatik>> {
        return repository.getAllBatik()
    }

    suspend fun getBatikById(idBatik : Long) : KatalogBatik{
        return repository.getBatikById(idBatik)
    }
}