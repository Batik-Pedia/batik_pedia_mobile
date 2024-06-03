package com.tricakrawala.batikpedia.domain.model

import com.tricakrawala.batikpedia.R

object FakeSourceBatik {

    val listRekomendasi = listOf(
        Rekomendasi(1, R.drawable.rekomendasi1),
        Rekomendasi(2, R.drawable.rekomendasi2),
        Rekomendasi(3, R.drawable.rekomendasi1),
        Rekomendasi(4, R.drawable.rekomendasi2),
        Rekomendasi(5, R.drawable.rekomendasi2),
    )

    val listVideoMembatik= listOf(
        VideoMembatik(1, R.drawable.videomembatik, "Tutorial Membatik Teknik Counting tulis-PART 1"),
        VideoMembatik(2, R.drawable.videomembatik, "Citra Alam"),
        VideoMembatik(3, R.drawable.kursus1, "Udemy"),
        VideoMembatik(4, R.drawable.kursus2, "Superprof")

    )

}