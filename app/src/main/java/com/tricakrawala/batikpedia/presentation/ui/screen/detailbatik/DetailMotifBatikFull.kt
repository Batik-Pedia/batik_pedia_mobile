package com.tricakrawala.batikpedia.presentation.ui.screen.detailbatik

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.tricakrawala.batikpedia.presentation.model.detailbatik.DetailBatikViewModel
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.presentation.ui.components.ImgDetailBig
import com.tricakrawala.batikpedia.presentation.ui.components.TextWithCard
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailMotifBatikFullScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailBatikViewModel = koinViewModel(),
    idBatik : Long,
    navController: NavHostController,
){

    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)

    LaunchedEffect(true) {
        if (uiState is UiState.Loading){
            viewModel.getAllWisata(idBatik)
        }
    }

    when(val batik = uiState){

        is UiState.Error -> {}
        is UiState.Success -> {
            DetailMotifBatikFullContent(imageBatik = batik.data.image, titleBatik = batik.data.namaMotif, navController = navController)
        }
        else -> {}
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMotifBatikFullContent(
    modifier: Modifier = Modifier,
    imageBatik : Int,
    titleBatik : String,
    navController : NavHostController,

){

    Box(
        modifier = Modifier
            .background(background2)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState())

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
                    text = stringResource(id = R.string.motif_batik),
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
                        modifier = Modifier.size(24.dp)
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
            ImgDetailBig(image = imageBatik, text = titleBatik, modifier = Modifier)

            Spacer(modifier = Modifier.height(8.dp))

            TextWithCard(title = stringResource(id = R.string.sejarah), text = stringResource(id = R.string.sejarah_detail))

            Spacer(modifier = Modifier.height(8.dp))

            TextWithCard(title = stringResource(id = R.string.penggunaan), text = stringResource(id = R.string.penggunaan_detail))

            Spacer(modifier = Modifier.height(8.dp))

            TextWithCard(title = stringResource(id = R.string.makna), text = stringResource(id = R.string.makna_detail))
            
        }
    }

}

@Composable
@Preview
private fun Preview(){
    BatikPediaTheme {
        DetailMotifBatikFullContent(imageBatik = R.drawable.batik1, titleBatik = "Batik Kawung", navController = rememberNavController())
    }
}