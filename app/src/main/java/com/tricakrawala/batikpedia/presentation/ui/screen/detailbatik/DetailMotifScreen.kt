package com.tricakrawala.batikpedia.presentation.ui.screen.detailbatik

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.data.resource.remote.response.KatalogId
import com.tricakrawala.batikpedia.presentation.model.detailbatik.DetailBatikViewModel
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.presentation.ui.components.AtlasItem
import com.tricakrawala.batikpedia.presentation.ui.components.ImgDetailBig
import com.tricakrawala.batikpedia.presentation.ui.components.LoadingData
import com.tricakrawala.batikpedia.presentation.ui.components.TextWithCard
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.primary
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMotifScreen(
    idBatik: Int,
    viewModel: DetailBatikViewModel = hiltViewModel(),
    navToBatikFullDetail: (Int) -> Unit,
    navController: NavHostController,
) {
    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)

    LaunchedEffect(true) {
        if (uiState is UiState.Loading) {
            viewModel.getDetailBatik(idBatik)
        }
    }

    when (val batik = uiState) {

        is UiState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                AlertDialog(
                    onDismissRequest = {},
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Transparent),
                ) {
                    LoadingData(Modifier.align(Alignment.Center), "Sedang Memuat Data..")
                }
            }
        }
        is UiState.Success -> {
            DetailMotifContent(
              katalogId = batik.data,
                navToBatikFullDetail = navToBatikFullDetail,
                navController = navController,
            )
        }

        else -> {}
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMotifContent(
    modifier: Modifier = Modifier,
    katalogId : KatalogId,
    navToBatikFullDetail: (Int) -> Unit,
    navController: NavHostController,
) {

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
                ImgDetailBig(image ="https://batikpedia-tricakrawala.domcloud.dev/images/${katalogId.image}", text = stringResource(id = R.string.motif_batik_detail, katalogId.namaBatik), modifier = Modifier)
                Spacer(modifier = Modifier.height(8.dp))

                Box {
                    TextWithCard(
                        title = stringResource(id = R.string.tentang_motif_batik),
                        text =katalogId.detailBatik
                    )

                    Text(
                        text = stringResource(id = R.string.selengkapnya),
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = primary,
                        fontSize = 10.sp,
                        modifier = modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .align(Alignment.BottomEnd)
                            .clickable { navToBatikFullDetail(katalogId.idBatik) }
                    )
                }

            Spacer(modifier = Modifier.height(8.dp))

            AtlasItem(
                longitude = katalogId.lon,
                latitude = katalogId.lat,
                nusantara = katalogId.wilayah,
                modifier = modifier.fillMaxWidth()
            )
        }
    }
}