package com.tricakrawala.batikpedia.domain.usecase

import com.tricakrawala.batikpedia.data.resource.local.datamodel.Nusantara
import com.tricakrawala.batikpedia.domain.repositories.BatikRepository
import kotlinx.coroutines.flow.Flow


class NusantaraUseCase(private val repository: BatikRepository) {

    fun getAllNusantara() : Flow<List<Nusantara>> {
        return  repository.getAllNusantara()
    }

    suspend fun getNusantaraById(idProvinsi: Long) : Nusantara {
        return repository.getProvinsiById(idProvinsi)
    }



}