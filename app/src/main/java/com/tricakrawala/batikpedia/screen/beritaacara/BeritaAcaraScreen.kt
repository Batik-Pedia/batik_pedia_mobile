package com.tricakrawala.batikpedia.screen.beritaacara

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.model.Berita
import com.tricakrawala.batikpedia.ui.common.UiState
import com.tricakrawala.batikpedia.ui.components.BeritaRow
import com.tricakrawala.batikpedia.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.ui.theme.background2
import com.tricakrawala.batikpedia.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.ui.theme.textColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun BeritaAcaraScreen(
    modifier: Modifier = Modifier,
    viewModel: BeritaViewModel = koinViewModel(),
    navController : NavHostController,

){

    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)

    LaunchedEffect(true) {
        if (uiState is UiState.Loading){
            viewModel.getAllBerita()
        }
    }

    when(val listBerita = uiState){
        is UiState.Error -> {}
        is UiState.Success -> {
            BeritaAcaraContent(listBerita = listBerita.data, navController = navController)
        }

        else -> {}
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeritaAcaraContent(
    modifier: Modifier = Modifier,
    listBerita: List<Berita>,
    navController : NavHostController,
){

    Box(
        modifier = Modifier
            .background(background2)
            .fillMaxSize()
            .statusBarsPadding()

    ) {
        Image(
            painter = painterResource(id = R.drawable.ornamen_batik_beranda),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(180.dp)

        )

        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.berita_acara),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor,
                    fontSize = 16.sp
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = stringResource(id = R.string.back),
                        modifier = Modifier.size(24.dp),
                        tint = textColor
                    )
                }
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp),
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent)
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 88.dp, start = 24.dp, end = 24.dp)


        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(end = 4.dp, start = 4.dp, bottom = 4.dp),
            ) {
                items(listBerita){data ->
                    BeritaRow(image = data.image, title = data.title, time = data.time, lokasi = data.lokasi)
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
private fun preview(){

    val listBerita = listOf(
        Berita(
            1,
            "Melihat Proses Pembuatan Batik Tulis dan Cap di Kauman Solo",
            R.drawable.fake_berita_image,
            "04 Apr 2024 08.00",
            "Solo, Indonesia"
        ),
        Berita(
            2,
            "Melihat Proses Pembuatan Batik Tulis dan Cap di Kauman Solo",
            R.drawable.fake_berita_image,
            "04 Apr 2024 08.00",
            "Solo, Indonesia"
        ),
        Berita(
            3,
            "Melihat Proses Pembuatan Batik Tulis dan Cap di Kauman Solo",
            R.drawable.fake_berita_image,
            "04 Apr 2024 08.00",
            "Solo, Indonesia"
        ),
        Berita(
            4,
            "Melihat Proses Pembuatan Batik Tulis dan Cap di Kauman Solo",
            R.drawable.fake_berita_image,
            "04 Apr 2024 08.00",
            "Solo, Indonesia"
        ),
        Berita(
            5,
            "Melihat Proses Pembuatan Batik Tulis dan Cap di Kauman Solo",
            R.drawable.fake_berita_image,
            "04 Apr 2024 08.00",
            "Solo, Indonesia"
        ),
        Berita(
            6,
            "Melihat Proses Pembuatan Batik Tulis dan Cap di Kauman Solo",
            R.drawable.fake_berita_image,
            "04 Apr 2024 08.00",
            "Solo, Indonesia"
        ),
    )

    BatikPediaTheme {
        BeritaAcaraContent(listBerita = listBerita, navController = rememberNavController())
    }
}


