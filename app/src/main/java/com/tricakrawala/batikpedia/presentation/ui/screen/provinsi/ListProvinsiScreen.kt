package com.tricakrawala.batikpedia.presentation.ui.screen.provinsi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.tricakrawala.batikpedia.data.resource.remote.response.ProvinsiItem
import com.tricakrawala.batikpedia.presentation.model.provinsi.ProvinsiViewModel
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.presentation.ui.components.ProvinsiItemRow
import com.tricakrawala.batikpedia.presentation.ui.components.SearchBarKatalog
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor

@Composable
fun ListProvinsiScreen(
    viewModel: ProvinsiViewModel = hiltViewModel(),
    navigateToDetail : (Long) -> Unit,
    navController: NavHostController
){

    val uiState by viewModel.uiState.collectAsState(initial = UiState.Loading)

    LaunchedEffect(true) {
        if (uiState is UiState.Loading) {
            viewModel.getAllNusantara()
        }
    }

    when (val nusantara = uiState) {
        is UiState.Success -> {
            ListProvinsiContent( navigateToDetail = navigateToDetail, listProvinsi = nusantara.data, navController = navController)
        }

        is UiState.Error -> {}

        is UiState.Loading -> {

        }
        else -> {

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListProvinsiContent(
    modifier: Modifier = Modifier,
    listProvinsi : List<ProvinsiItem>,
    navigateToDetail: (Long) -> Unit,
    navController : NavHostController,
){
    var query by remember { mutableStateOf("") }
    val filteredList = remember(query, listProvinsi) {
        if (query.isEmpty()) {
            listProvinsi
        } else {
            listProvinsi.filter {
                it.namaProvinsi.contains(query, ignoreCase = true)
            }
        }
    }

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
                        tint = textColor,
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
            SearchBarKatalog(
                query = query,
                onQueryChange = { newQuery -> query = newQuery },
                modifier = Modifier
                    .fillMaxWidth()
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(end = 4.dp, start = 4.dp, bottom = 4.dp),
            ) {
                items(filteredList){data ->
                    ProvinsiItemRow(image = data.imgProvinsi, provinsi = data.namaProvinsi, modifier = modifier.clickable { navigateToDetail(data.idProvinsi.toLong())})
                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview(){
    BatikPediaTheme {

        ListProvinsiContent(listProvinsi = emptyList(), navController = rememberNavController(), navigateToDetail = {})
    }
}