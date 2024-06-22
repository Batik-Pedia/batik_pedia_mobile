package com.tricakrawala.batikpedia.presentation.ui.screen.provinsi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.BasicAlertDialog
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogBatikItem
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataItem
import com.tricakrawala.batikpedia.presentation.model.provinsi.ProvinsiViewModel
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.presentation.ui.components.ImgDetailBig
import com.tricakrawala.batikpedia.presentation.ui.components.ImgRowDetail
import com.tricakrawala.batikpedia.presentation.ui.components.LoadingData
import com.tricakrawala.batikpedia.presentation.ui.components.TextWithCard
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProvinsiScreen(
    idProvinsi: Long,
    viewModel: ProvinsiViewModel = hiltViewModel(),
    navigateToWisata: (Long) -> Unit,
    navToDetailBatik : (Int) -> Unit,
    navController: NavHostController,
) {

    val uiState by viewModel.uiStateDetail.collectAsState(initial = UiState.Loading)
    val uiStateBatik by viewModel.uiStateBatik.collectAsState(initial = UiState.Loading)
    val uiStateWisata by viewModel.uiStateWisata.collectAsState(initial = UiState.Loading)

    LaunchedEffect(true) {
        if (uiState is UiState.Loading || uiStateWisata is UiState.Loading || uiStateBatik is UiState.Loading) {
            viewModel.getProvinsiById(idProvinsi)
            viewModel.getAllBatik()
            viewModel.getAllWisata()
        }

    }

    val provinsiState = uiState
    val batikState = uiStateBatik
    val wisataState = uiStateWisata

    when {
        provinsiState is UiState.Success && batikState is UiState.Success && wisataState is UiState.Success -> {
            DetailProvinsiContent(
                navController = navController,
                image = provinsiState.data.imgProvinsi,
                textContent = provinsiState.data.namaProvinsi,
                listBatik = batikState.data,
                listWisata = wisataState.data,
                navigateToWisata = navigateToWisata,
                detailProv = provinsiState.data.detailProvinsi,
                navToDetailBatik = navToDetailBatik
            )
        }
        provinsiState is UiState.Loading || batikState is UiState.Loading || wisataState is UiState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                BasicAlertDialog(
                    onDismissRequest = {},
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Transparent)
                ) {
                    LoadingData(Modifier.align(Alignment.Center), "Sedang Memuat Data..")
                }
            }

        }
        else -> { }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailProvinsiContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    image: String,
    detailProv : String,
    listBatik: List<KatalogBatikItem>,
    listWisata: List<WisataItem>,
    textContent: String,
    navigateToWisata: (Long) -> Unit,
    navToDetailBatik : (Int) -> Unit,
) {

    val filteredListBatik = remember(textContent, listBatik) {
        listBatik.filter {
            it.wilayah.contains(textContent, ignoreCase = true)
        }
    }

    val filteredListWisata = remember(textContent, listWisata) {
        listWisata.filter {
            it.wilayah.contains(textContent, ignoreCase = true)
        }
    }

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
                    text = stringResource(id = R.string.menu_provinsi),
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
            ImgDetailBig(image = image, text = textContent, modifier = Modifier)
            Spacer(modifier = Modifier.height(8.dp))
            TextWithCard(text = detailProv)

            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.motif_batik),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                )
                if (filteredListBatik.isEmpty()) {
                    Text(
                        text = stringResource(R.string.batik_di_wilayah_ini_belum_tersedia),
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = textColor,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 16.dp)
                    )
                }else{
                    LazyRow(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(horizontal = 16.dp)
                    ) {

                        items(filteredListBatik){batik ->
                            ImgRowDetail(image = batik.image, modifier = Modifier.padding(end = 16.dp).clickable {
                                navToDetailBatik(batik.idBatik)
                            } )
                        }
                    }
                }
                
                Text(
                    text = stringResource(id = R.string.destinasi_wisata),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp)
                )
                if (filteredListWisata.isEmpty()){
                    Text(
                        text = stringResource(R.string.wisata_di_wilayah_ini_belum_tersedia),
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = textColor,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    )
                }else{
                    LazyRow(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    ) {

                        items(filteredListWisata){wisata ->
                            ImgRowDetail(image = wisata.imageWisata, modifier = modifier
                                .padding(end = 16.dp)
                                .clickable { navigateToWisata(wisata.idWisata.toLong()) })
                        }
                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun Preview() {


    BatikPediaTheme {
        DetailProvinsiContent(
            navController = rememberNavController(),
            image = "R.drawable.yogyakarta",
            textContent = "Yogyakarta",
            listBatik = emptyList(),
            listWisata = emptyList(),
            navigateToWisata = {},
            navToDetailBatik = {},
            detailProv = ""
        )
    }
}