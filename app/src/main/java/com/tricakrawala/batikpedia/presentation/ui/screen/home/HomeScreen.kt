package com.tricakrawala.batikpedia.presentation.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.data.resource.remote.response.BeritaItem
import com.tricakrawala.batikpedia.data.resource.remote.response.ProvinsiItem
import com.tricakrawala.batikpedia.domain.model.FakeSourceBatik
import com.tricakrawala.batikpedia.domain.model.Rekomendasi
import com.tricakrawala.batikpedia.presentation.model.home.HomeViewModel
import com.tricakrawala.batikpedia.presentation.navigation.Screen
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.presentation.ui.components.CardBerita
import com.tricakrawala.batikpedia.presentation.ui.components.LoadingData
import com.tricakrawala.batikpedia.presentation.ui.components.NavbarHome
import com.tricakrawala.batikpedia.presentation.ui.components.NusantaraItemRow
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.primary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToNusantara: (Long) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val uiStateNusantara by viewModel.uiStateNusantara.collectAsState(initial = UiState.Loading)
    val uiStateRekomendasi by viewModel.uiStateRekomendasi.collectAsState(initial = UiState.Loading)
    val uiStateBerita by viewModel.uiState.collectAsState(initial = UiState.Loading)

    LaunchedEffect(true) {
        if (uiStateNusantara is UiState.Loading || uiStateRekomendasi is UiState.Loading || uiStateBerita is UiState.Loading) {
            viewModel.getAllNusantara()
            viewModel.getAllRekomendasi()
            viewModel.getAllBerita()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val nusantaraState = uiStateNusantara
        val rekomendasiState = uiStateRekomendasi
        val beritaState = uiStateBerita

        when {
            nusantaraState is UiState.Success && rekomendasiState is UiState.Success && beritaState is UiState.Success -> {
                HomeContent(
                    navigateToNusantara = navigateToNusantara,
                    listNusantara = nusantaraState.data,
                    listRekomendasi = rekomendasiState.data,
                    berita = beritaState.data,
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                )
            }

            nusantaraState is UiState.Loading || rekomendasiState is UiState.Loading || beritaState is UiState.Loading -> {
                BasicAlertDialog(
                    onDismissRequest = {},
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Transparent),
                ) {
                    LoadingData(modifier.align(Alignment.Center), "Sedang Memuat Data..")
                }
            }

            else -> {}
        }

    }
}


@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    navigateToNusantara: (Long) -> Unit,
    listNusantara: List<ProvinsiItem>,
    listRekomendasi: List<Rekomendasi>,
    berita: List<BeritaItem>,
    navController: NavHostController,
) {
    val randomBerita = berita.shuffled().firstOrNull()

    Box(
        modifier = Modifier
            .background(background2)
            .fillMaxSize()
            .statusBarsPadding()

    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_logo_batik_pedia),
            contentDescription = "logo batik pedia",
            modifier = Modifier
                .padding(start = 24.dp, top = 36.dp)
        )
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_teks_logo_batik_pedia),
            contentDescription = "logo batik pedia",
            modifier = Modifier
                .padding(start = 68.dp, top = 42.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ornamen_batik_beranda),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(180.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 88.dp, start = 24.dp, end = 24.dp)
        ) {

            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .background(primary)
                    .align(Alignment.CenterHorizontally)
                    .clickable { navController.navigate(Screen.Berita.route) }
            ) {
                CardBerita(
                    image = randomBerita?.imageBerita ?: "",
                    text = randomBerita?.namaBerita ?: "",
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 12.dp, vertical = 16.dp)
                )
            }
            NavbarHome(
                textContent = stringResource(id = R.string.jelajahi_nusantara),
                modifier = Modifier.clickable {
                    navController.navigate(
                        Screen.ToListProvinsi.route
                    )
                })

            LazyRow {
                items(listNusantara) { data ->
                    NusantaraItemRow(
                        provinsi = data.namaProvinsi,
                        image = data.imgProvinsi,
                        modifier = modifier.clickable { navigateToNusantara(data.idProvinsi.toLong()) }
                    )
                }
            }

            NavbarHome(textContent = stringResource(id = R.string.rekomendasi_untuk_anda))

            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .height(300.dp)
            ) {
                items(listRekomendasi) { data ->
                    Image(
                        painter = painterResource(id = data.image),
                        contentDescription = "Rekomendasi",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(150.dp)
                            .clip(RoundedCornerShape(16.dp))

                    )
                }
                item{
                    Spacer(modifier = Modifier.height(120.dp))
                }

            }

        }
    }

}

@Preview
@Composable
private fun Preview() {


    BatikPediaTheme {
        HomeContent(
            navigateToNusantara = { },
            listNusantara = emptyList(),
            listRekomendasi = FakeSourceBatik.listRekomendasi,
            navController = rememberNavController(),
            berita = emptyList()
        )
    }
}