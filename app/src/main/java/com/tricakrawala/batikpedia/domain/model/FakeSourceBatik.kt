package com.tricakrawala.batikpedia.domain.model

import com.tricakrawala.batikpedia.R

object FakeSourceBatik {

    val listRekomendasi = listOf(
        Rekomendasi(1, R.drawable.rekomendasi1),
        Rekomendasi(2, R.drawable.rekomendasi2),
        Rekomendasi(3, R.drawable.dummyrekom),
        Rekomendasi(4, R.drawable.dummyrekom2),
    )

    val listVideoMembatik= listOf(
        VideoMembatik(1, R.drawable.videomembatik, "Tutorial Membatik Teknik Counting tulis-PART 1"),
        VideoMembatik(2, R.drawable.videomembatik2, "Tutorial Membatik Teknik Counting tulis-PART 2"),

    )

}