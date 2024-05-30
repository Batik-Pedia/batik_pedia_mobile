package com.tricakrawala.batikpedia.domain.usecase

import com.tricakrawala.batikpedia.data.resource.local.datamodel.KursusBatik
import com.tricakrawala.batikpedia.data.resource.local.datamodel.VideoMembatik
import com.tricakrawala.batikpedia.domain.repositories.BatikRepository
import kotlinx.coroutines.flow.Flow

class CourseUseCase(private val repository: BatikRepository) {
    fun getAllKursus(): Flow<List<KursusBatik>>{
        return repository.getAllKursus()
    }

    suspend fun getKursusById(idKursus : Long) : KursusBatik{
        return repository.getKursusById(idKursus)
    }

    fun getAllVideo(): Flow<List<VideoMembatik>>{
        return repository.getAllVideo()
    }

}