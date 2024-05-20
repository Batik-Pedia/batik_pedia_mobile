package com.tricakrawala.batikpedia.model

import com.tricakrawala.batikpedia.R

object FakeSourceBatik {

    val listNusantara = listOf(
        Nusantara(1, R.drawable.yogyakarta, "Yogyakarta", 1, 1),
        Nusantara(2, R.drawable.yogyakarta, "jawa Timur", 2, 2),
        Nusantara(3, R.drawable.yogyakarta, "Jawa Tengah", 3, 3),
        Nusantara(4, R.drawable.yogyakarta, "Jawa Barat", 4, 4),
        Nusantara(5, R.drawable.yogyakarta, "Bali", 4, 4),
    )

    val listRekomendasi = listOf(
        Rekomendasi(1, R.drawable.rekomendasi1),
        Rekomendasi(2, R.drawable.rekomendasi2),
        Rekomendasi(3, R.drawable.rekomendasi1),
        Rekomendasi(4, R.drawable.rekomendasi2),
        Rekomendasi(5, R.drawable.rekomendasi2),
    )

    val listBatik = listOf(
        KatalogBatik(1, R.drawable.batik1, "Motif Batik Kawung", "Motif Batik Megamendung"),
        KatalogBatik(2, R.drawable.batik1, "Motif Batik Kawung", "Motif Batik Megamendung"),
        KatalogBatik(3, R.drawable.batik1, "Motif Batik Kawung", "Motif Batik Megamendung"),
        KatalogBatik(4, R.drawable.batik1, "Motif Batik Kawung", "Motif Batik Megamendung"),
        KatalogBatik(5, R.drawable.batik1, "Motif Batik Kawung", "Motif Batik Megamendung"),

        )

    val listWisata = listOf(
        Wisata(1, R.drawable.wisata1, "Kampung Batik Laweyan"),
        Wisata(2, R.drawable.wisata1, "Kampung Batik Laweyan"),
        Wisata(3, R.drawable.wisata1, "Kampung Batik Laweyan"),
        Wisata(4, R.drawable.wisata1, "Kampung Batik Laweyan"),
        Wisata(5, R.drawable.wisata1, "Kampung Batik Laweyan"),
        Wisata(6, R.drawable.wisata1, "Kampung Batik Laweyan"),
        Wisata(7, R.drawable.wisata1, "Kampung Batik Laweyan"),
        Wisata(8, R.drawable.wisata1, "Kampung Batik Laweyan"),
    )

    val listBerita = listOf(
        Berita(
            1,
            "https://www.detik.com/jateng/wisata/d-7277135/melihat-proses-pembuatan-batik-tulis-dan-cap-di-kauman-solo",
            "Melihat Proses Pembuatan Batik Tulis dan Cap di Kauman Solo",
            R.drawable.fake_berita_image,
            "04 Apr 2024 08.00",
            "Solo, Indonesia"
        ),
        Berita(
            2,
            "https://www.detik.com/jateng/wisata/d-7277135/melihat-proses-pembuatan-batik-tulis-dan-cap-di-kauman-solo",
            "Melihat Proses Pembuatan Batik Tulis dan Cap di Kauman Solo",
            R.drawable.fake_berita_image,
            "04 Apr 2024 08.00",
            "Solo, Indonesia"
        ),
        Berita(
            3,
            "https://www.detik.com/jateng/wisata/d-7277135/melihat-proses-pembuatan-batik-tulis-dan-cap-di-kauman-solo",
            "Melihat Proses Pembuatan Batik Tulis dan Cap di Kauman Solo",
            R.drawable.fake_berita_image,
            "04 Apr 2024 08.00",
            "Solo, Indonesia"
        ),
        Berita(
            4,
            "https://www.detik.com/jateng/wisata/d-7277135/melihat-proses-pembuatan-batik-tulis-dan-cap-di-kauman-solo",
            "Melihat Proses Pembuatan Batik Tulis dan Cap di Kauman Solo",
            R.drawable.fake_berita_image,
            "04 Apr 2024 08.00",
            "Solo, Indonesia"
        ),
        Berita(
            5,
            "https://www.detik.com/jateng/wisata/d-7277135/melihat-proses-pembuatan-batik-tulis-dan-cap-di-kauman-solo",
            "Melihat Proses Pembuatan Batik Tulis dan Cap di Kauman Solo",
            R.drawable.fake_berita_image,
            "04 Apr 2024 08.00",
            "Solo, Indonesia"
        ),
        Berita(
            6,
            "https://www.detik.com/jateng/wisata/d-7277135/melihat-proses-pembuatan-batik-tulis-dan-cap-di-kauman-solo",
            "Melihat Proses Pembuatan Batik Tulis dan Cap di Kauman Solo",
            R.drawable.fake_berita_image,
            "04 Apr 2024 08.00",
            "Solo, Indonesia"
        ),
    )
    val listKursus = listOf(
        KursusBatik(1, R.drawable.kursus1, "Superprof"),
        KursusBatik(2, R.drawable.kursus1, "Citra Alam"),
        KursusBatik(3, R.drawable.kursus1, "Udemy"),
        KursusBatik(4, R.drawable.kursus2, "Superprof"),
        KursusBatik(1, R.drawable.kursus1, "Superprof"),
        KursusBatik(2, R.drawable.kursus1, "Citra Alam"),
        KursusBatik(3, R.drawable.kursus1, "Udemy"),
        KursusBatik(4, R.drawable.kursus2, "Superprof")

    )
    val listVideoMembatik= listOf(
        VideoMembatik(1, R.drawable.videomembatik, "Tutorial Membatik Teknik Counting tulis-PART 1"),
        VideoMembatik(2, R.drawable.videomembatik, "Citra Alam"),
        VideoMembatik(3, R.drawable.kursus1, "Udemy"),
        VideoMembatik(4, R.drawable.kursus2, "Superprof")

    )

}