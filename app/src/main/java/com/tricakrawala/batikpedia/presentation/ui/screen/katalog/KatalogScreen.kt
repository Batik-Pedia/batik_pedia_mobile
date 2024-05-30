package com.tricakrawala.batikpedia.presentation.ui.screen.katalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.data.resource.local.datamodel.KatalogBatik
import com.tricakrawala.batikpedia.presentation.model.katalog.KatalogViewModel
import com.tricakrawala.batikpedia.presentation.navigation.Screen
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.presentation.ui.components.KatalogItemRow
import com.tricakrawala.batikpedia.presentation.ui.components.SearchBarKatalog
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.primary
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun KatalogScreen(
    modifier: Modifier = Modifier,
    viewModel: KatalogViewModel = koinViewModel(),
    navToDetail : (Long) -> Unit,
    navController : NavHostController,
) {
    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)
    LaunchedEffect(true) {
        if (uiState is UiState.Loading) {
            viewModel.getAllBatik()
        }
    }

    when (val batik = uiState) {
        is UiState.Success -> {
            KatalogContent(listBatik = batik.data, navToDetail = navToDetail, navController = navController)
        }

        is UiState.Error -> {}
        else -> {

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KatalogContent(
    modifier: Modifier = Modifier,
    listBatik: List<KatalogBatik>,
    navToDetail : (Long) -> Unit,
    navController : NavHostController,
) {
    var query by remember { mutableStateOf("") }
//    val focusManager = LocalFocusManager.current
//    focusManager.clearFocus()


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
                    text = stringResource(id = R.string.menu_katalog),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = textColor,
                    fontSize = 16.sp
                )
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                SearchBarKatalog(
                    query = query,
                    onQueryChange = { newQuery -> query = newQuery },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp)
                )


                Box(
                    modifier = modifier
                        .align(Alignment.CenterVertically)
                        .clip(RoundedCornerShape(10.dp))
                        .size(56.dp)
                        .background(primary)
                        .clickable { navController.navigate(Screen.Filter.route) },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_filter_catalog),
                        contentDescription = "Filter",
                        tint = Color.White,
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }

            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp)

            ) {
                items(listBatik) { data ->
                    KatalogItemRow(
                        image = data.image,
                        motif = data.namaMotif,
                        jenis = data.jenisBatik,
                        modifier = modifier.clickable { navToDetail(data.idBatik) }
                    )
                }

            }

        }
    }

}

@Preview
@Composable
private fun preview() {
    val dummyBatik = listOf(
        KatalogBatik(1, R.drawable.batik1, "Mega Mendung", "Batik Tradisional"),
        KatalogBatik(2, R.drawable.batik1, "Mega Mendung", "Batik Tradisional"),
        KatalogBatik(3, R.drawable.batik1, "Mega Mendung", "Batik Tradisional"),
        KatalogBatik(4, R.drawable.batik1, "Mega Mendung", "Batik Tradisional"),
        KatalogBatik(5, R.drawable.batik1, "Mega Mendung", "Batik Tradisional"),
        KatalogBatik(6, R.drawable.batik1, "Mega Mendung", "Batik Tradisional"),
        KatalogBatik(7, R.drawable.batik1, "Mega Mendung", "Batik Tradisional"),
        KatalogBatik(8, R.drawable.batik1, "Mega Mendung", "Batik Tradisional"),
        KatalogBatik(9, R.drawable.batik1, "Mega Mendung", "Batik Tradisional"),
    )
    BatikPediaTheme {
        KatalogContent(listBatik = dummyBatik, navToDetail = {}, navController = rememberNavController())
    }
}