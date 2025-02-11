package com.tricakrawala.batikpedia.presentation.ui.screen.wisata

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.data.resource.remote.response.WisataId
import com.tricakrawala.batikpedia.presentation.model.wisata.WisataViewModel
import com.tricakrawala.batikpedia.presentation.navigation.Screen
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.presentation.ui.components.AtlasItem
import com.tricakrawala.batikpedia.presentation.ui.components.ImgDetailBig
import com.tricakrawala.batikpedia.presentation.ui.components.LoadingData
import com.tricakrawala.batikpedia.presentation.ui.components.TextWithCard
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailWisataScreen(
    idWisata: Long,
    navController: NavHostController,
    viewModel: WisataViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiStateWisataById.collectAsState(initial = UiState.Loading)

    if (uiState is UiState.Loading) {
        viewModel.getWisataById(idWisata)
    }

    when (val wisata = uiState) {
        is UiState.Loading -> {
            Box(Modifier.fillMaxSize()) {
                BasicAlertDialog(
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
            DetailWisataContent(
                navController = navController,
                wisata = wisata.data,
                viewModel = viewModel
            )
        }

        else -> {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailWisataContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    wisata: WisataId,
    viewModel: WisataViewModel
) {
    val favoriteList by viewModel.listFavorite.collectAsState()
    val isFavorite = favoriteList is UiState.Success &&
            (favoriteList as UiState.Success<List<WisataId>>).data.any { it.idWisata == wisata.idWisata }


    Box(
        modifier = Modifier
            .background(background2)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
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
                    text = stringResource(id = R.string.menu_wisata_top_bar),
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
                .verticalScroll(rememberScrollState())
        ) {
            ImgDetailBig(
                image = wisata.imageWisata,
                text = wisata.namaWisata,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextWithCard(
                title = stringResource(id = R.string.tentang_destinasi),
                text = wisata.detailWisata
            )

            Spacer(modifier = Modifier.height(8.dp))

            AtlasItem(
                latitude = wisata.lat,
                longitude = wisata.lon,
                nusantara = wisata.namaWisata,
                modifier = modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(36.dp))
        }

        FloatingActionButton(
            onClick = {

                val wisataFavorite = WisataId(
                    idWisata = wisata.idWisata,
                    namaWisata = wisata.namaWisata,
                    detailWisata = wisata.detailWisata,
                    imageWisata = wisata.imageWisata,
                    lat = wisata.lat,
                    lon = wisata.lon,
                    wilayah = wisata.wilayah,
                    isFavorite = true
                )

                if (isFavorite){
                    viewModel.deleteFavorite(wisataFavorite.idWisata)
                }else{
                    viewModel.insertFavoriteWisata(wisataFavorite)
                }

                Log.d("insert favorite", "DetailWisataContent: $wisata")
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = stringResource(id = R.string.favorite),
                tint = if (isFavorite) Color.Red else Color.White
            )
        }

    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//private fun InsertState(
//    viewModel: WisataViewModel,
//    wisata: WisataId?,
//) {
//
//    var isDialogVisible by remember { mutableStateOf(true) }
//
//    if (isDialogVisible){
//        when (val result = insertState) {
//            is UiState.Error -> {
//                Log.d("InsertFavorite", "InsertState: ${result.errorMessage}")
//                Box(Modifier.fillMaxSize()) {
//                    BasicAlertDialog(
//                        onDismissRequest = {
//                            isDialogVisible = false
//                        },
//                        modifier = Modifier
//                            .clip(RoundedCornerShape(16.dp))
//                            .background(Color.Transparent),
//                    ) {
//                        Text(
//                            text = "Gagal menambahkan ke favorite karena ${result.errorMessage}",
//                            fontFamily = poppinsFontFamily,
//                            fontWeight = FontWeight.Normal,
//                            fontSize = 14.sp,
//                            color = Color.White,
//                            modifier = Modifier.padding(
//                                start = 16.dp,
//                                end = 16.dp
//                            )
//                        )
//                    }
//                }
//            }
//
//            UiState.Loading -> {
//                Box(Modifier.fillMaxSize()) {
//                    BasicAlertDialog(
//                        onDismissRequest = {
//                            isDialogVisible = false
//                        },
//                        modifier = Modifier
//                            .clip(RoundedCornerShape(16.dp))
//                            .background(Color.Transparent),
//                    ) {
//                        LoadingData(Modifier.align(Alignment.Center), "Menambahkan ke favorit")
//                    }
//                }
//            }
//
//            is UiState.Success -> {
//                Box(
//                    Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    BasicAlertDialog(
//                        onDismissRequest = {
//                            isDialogVisible = false
//                        },
//                        modifier = Modifier
//                            .clip(RoundedCornerShape(16.dp))
//                            .background(Color.Transparent),
//                    ) {
//                        Text(
//                            text = "Berhasil menambahkan ke favorit ${wisata?.namaWisata}",
//                            fontFamily = poppinsFontFamily,
//                            fontWeight = FontWeight.Normal,
//                            fontSize = 14.sp,
//                            color = Color.White,
//                            modifier = Modifier.padding(
//                                start = 16.dp,
//                                end = 16.dp
//                            )
//                        )
//                    }
//
//                }
//            }
//        }
//    }
//
//}


@Composable
@Preview(showBackground = true)
private fun Preview() {
    BatikPediaTheme {
//        DetailWisataContent(navController = rememberNavController())
    }
}