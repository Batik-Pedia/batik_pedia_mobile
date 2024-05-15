package com.tricakrawala.batikpedia.data

import com.tricakrawala.batikpedia.model.Berita
import com.tricakrawala.batikpedia.model.FakeSourceBatik
import com.tricakrawala.batikpedia.model.KatalogBatik
import com.tricakrawala.batikpedia.model.KursusBatik
import com.tricakrawala.batikpedia.model.Nusantara
import com.tricakrawala.batikpedia.model.Rekomendasi
import com.tricakrawala.batikpedia.model.VideoMembatik
import com.tricakrawala.batikpedia.model.Wisata
import com.tricakrawala.batikpedia.pref.UserModel
import com.tricakrawala.batikpedia.pref.UserPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class BatikRepository(private val preference: UserPreference) {

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

    suspend fun saveSession(user: UserModel) {
        preference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return preference.getSession()
    }


    fun getAllNusantara(): Flow<List<Nusantara>> {
        return flowOf(nusantaraList)
    }

    fun getAllRekomendasi(): Flow<List<Rekomendasi>> {
        return flowOf(rekomendasiList)
    }

    fun getAllBatik(): Flow<List<KatalogBatik>> {
        return flowOf(batikList)
    }

    fun getBatikById(idBatik : Long) = batikList.first { it.idBatik == idBatik }

    fun getAllWisata(): Flow<List<Wisata>> {
        return flowOf(wisataList)
    }

    fun getWisataById(idWisata : Long) = wisataList.first{it.idWisata == idWisata}

    fun getAllBerita() = flowOf(beritaList)

    fun getProvinsiById(idProvinsi : Long) : Nusantara = nusantaraList.first { it.idNusantara == idProvinsi }
    fun getAllKursus(): Flow<List<KursusBatik>> {
        return flowOf(kursusList)
    }
  fun getKursusById(idKursus : Long) = kursusList.first { it.idKursus == idKursus}
   fun getKursusById(idKursus : Long) = kursusList.first { it.idKursus == idKursus }
    fun getAllVideo(): Flow<List<VideoMembatik>> {
        return flowOf(videoList)
    }

}