package com.tricakrawala.batikpedia.data.repositories

import com.tricakrawala.batikpedia.data.pref.UserModel
import com.tricakrawala.batikpedia.data.pref.UserPreference
import com.tricakrawala.batikpedia.domain.repositories.BatikRepository
import com.tricakrawala.batikpedia.data.resource.local.datamodel.Berita
import com.tricakrawala.batikpedia.data.resource.local.datamodel.FakeSourceBatik
import com.tricakrawala.batikpedia.data.resource.local.datamodel.KatalogBatik
import com.tricakrawala.batikpedia.data.resource.local.datamodel.KursusBatik
import com.tricakrawala.batikpedia.data.resource.local.datamodel.Nusantara
import com.tricakrawala.batikpedia.data.resource.local.datamodel.Rekomendasi
import com.tricakrawala.batikpedia.data.resource.local.datamodel.VideoMembatik
import com.tricakrawala.batikpedia.data.resource.local.datamodel.Wisata
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class BatikRepositoryImpl(private val preference: UserPreference) : BatikRepository {

    private val nusantaraList = mutableListOf<Nusantara>()
    private val rekomendasiList = mutableListOf<Rekomendasi>()
    private val batikList = mutableListOf<KatalogBatik>()
    private val wisataList = mutableListOf<Wisata>()
    private val beritaList = mutableListOf<Berita>()
    private val kursusList = mutableListOf<KursusBatik>()
    private val videoList = mutableListOf<VideoMembatik>()

    init{
        if (nusantaraList.isEmpty()){
            FakeSourceBatik.listNusantara.forEach {
                nusantaraList.add(it)
            }
        }

        if (rekomendasiList.isEmpty()){
            FakeSourceBatik.listRekomendasi.forEach {
                rekomendasiList.add(it)
            }
        }

        if (batikList.isEmpty()){
            FakeSourceBatik.listBatik.forEach {
                batikList.add(it)
            }
        }

        if (wisataList.isEmpty()){
            FakeSourceBatik.listWisata.forEach {
                wisataList.add(it)
            }
        }

        if(beritaList.isEmpty()){
            FakeSourceBatik.listBerita.forEach {
                beritaList.add(it)
            }
        }
        if (kursusList.isEmpty()){
            FakeSourceBatik.listKursus.forEach {
                kursusList.add(it)
            }
        }
        if (videoList.isEmpty()){
            FakeSourceBatik.listVideoMembatik.forEach {
                videoList.add(it)
            }
        }
    }

    override suspend fun saveSession(user: UserModel) {
        preference.saveSession(user)
    }

    override fun getSession(): Flow<UserModel> {
        return preference.getSession()
    }

    override fun getAllNusantara(): Flow<List<Nusantara>> {
        return flowOf(nusantaraList)
    }

    override fun getAllRekomendasi(): Flow<List<Rekomendasi>> {
        return flowOf(rekomendasiList)
    }

    override fun getAllBatik(): Flow<List<KatalogBatik>> {
        return flowOf(batikList)
    }

    override suspend fun getBatikById(idBatik: Long): KatalogBatik {
        return batikList.first { it.idBatik == idBatik }
    }

    override fun getAllWisata(): Flow<List<Wisata>> {
        return flowOf(wisataList)
    }

    override suspend fun getWisataById(idWisata: Long): Wisata {
        return wisataList.first{it.idWisata == idWisata}
    }

    override fun getAllBerita(): Flow<List<Berita>> {
        return flowOf(beritaList)
    }

    override suspend fun getBeritaById(idBerita: Long): Berita {
        return beritaList.first { it.idBerita == idBerita }
    }

    override suspend fun getProvinsiById(idProvinsi: Long): Nusantara {
        return nusantaraList.first { it.idNusantara == idProvinsi }
    }

    override fun getAllKursus(): Flow<List<KursusBatik>> {
        return flowOf(kursusList)
    }

    override suspend fun getKursusById(idKursus: Long): KursusBatik {
        return kursusList.first { it.idKursus == idKursus }
    }

    override fun getAllVideo(): Flow<List<VideoMembatik>> {
        return flowOf(videoList)
    }

}