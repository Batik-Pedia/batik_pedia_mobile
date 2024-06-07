package com.tricakrawala.batikpedia.presentation.ui.screen.filter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.data.pref.FilterState
import com.tricakrawala.batikpedia.presentation.model.katalog.KatalogViewModel
import com.tricakrawala.batikpedia.presentation.navigation.Screen
import com.tricakrawala.batikpedia.presentation.ui.common.UiState
import com.tricakrawala.batikpedia.presentation.ui.components.PopupFilter
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.background2
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.primary
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor
import com.tricakrawala.batikpedia.utils.Utils

@Composable
fun FilterScreen(
    navController: NavHostController,
) {
    FilterContent(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: KatalogViewModel = hiltViewModel()
) {

    val filterUiState by viewModel.filterUiState.collectAsState()

    val urutan = listOf("Urutkan dari atau ke bawah", "Urutkan dari bawah ke atas")
    val jenis = listOf("Semua","Batik Tradisional", "Batik Modern")
    val areas = Utils.wilayah
    var selectedOption by remember {
        mutableStateOf(urutan[0])
    }

    var jenisBatik by remember { mutableStateOf(jenis[0]) }
    var selectedArea by remember { mutableStateOf(areas[0]) }

    var dropdownExpanded by remember { mutableStateOf(false) }
    val saveState by viewModel.saveState.collectAsState()


    LaunchedEffect(filterUiState) {
        if (filterUiState is UiState.Success) {
            val filter = (filterUiState as UiState.Success<FilterState>).data

            jenisBatik = when (filter.jenisBatik) {
                "Semua" -> jenis[0]
                "Tradisional" -> jenis[1]
                "Modern" -> jenis[2]
                else -> jenis[0]
            }
            selectedOption = if (filter.sort == "asc") urutan[0] else urutan[1]
            selectedArea = filter.wilayah
        }
    }

    fun showPopup() {
        dropdownExpanded = true
    }

    fun closePopup() {
        dropdownExpanded = false
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
                    text = stringResource(id = R.string.filter),
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
            Text(
                text = stringResource(id = R.string.urutan),
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = textColor
            )

            urutan.forEach { pilihan ->
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = selectedOption == pilihan,
                            onClick = { selectedOption = pilihan },
                            role = Role.RadioButton
                        )
                ) {
                    IconToggleButton(
                        checked = selectedOption == pilihan,
                        onCheckedChange = { selectedOption = pilihan },
                        content = {
                            Icon(
                                painter = painterResource(if (selectedOption == pilihan) R.drawable.ic_checked else R.drawable.ic_check),
                                contentDescription = "Radio button icon",
                                tint = primary
                            )
                        }
                    )
                    Text(
                        text = pilihan,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = textColor,
                        modifier = modifier
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            Spacer(modifier = modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.wilayah),
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = textColor
            )

            OutlinedTextField(
                value = selectedArea,
                onValueChange = { newValue -> selectedArea = newValue },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { showPopup() },
                trailingIcon = {
                    IconButton(
                        onClick = { showPopup() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Dropdown Icon"
                        )
                    }
                },
                readOnly = true, // TextField hanya bisa dibaca
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedBorderColor = primary,
                    unfocusedBorderColor = primary,
                    textColor = textColor
                ),
                shape = RoundedCornerShape(16.dp),
            )

            if (dropdownExpanded) {
                PopupFilter(
                    items = areas,
                    selectedItems = areas.map { it == selectedArea },
                    onDismissRequest = { closePopup() },
                    onItemSelected = { index ->
                        closePopup()
                        selectedArea = areas[index]
                    }
                )
            }

            Spacer(modifier = modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.jenis_batik),
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = textColor
            )

            jenis.forEach { sortJenis ->
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = jenisBatik == sortJenis,
                            onClick = { jenisBatik = sortJenis },
                            role = Role.RadioButton
                        )
                ) {
                    IconToggleButton(
                        checked = jenisBatik == sortJenis,
                        onCheckedChange = { jenisBatik = sortJenis },
                        content = {
                            Icon(
                                painter = painterResource(if (jenisBatik == sortJenis) R.drawable.ic_checked else R.drawable.ic_check),
                                contentDescription = "Radio button icon",
                                tint = primary
                            )
                        }
                    )
                    Text(
                        text = sortJenis,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = textColor,
                        modifier = modifier
                            .align(Alignment.CenterVertically)
                    )
                }
            }

            Spacer(modifier = modifier.weight(1f))

            Button(
                onClick = {
                    val filterState = FilterState(
                        sort = if (selectedOption == urutan[0]) "asc" else "desc",
                        wilayah = selectedArea,
                        jenisBatik = when(jenisBatik){
                            jenis[0] -> "Semua"
                            jenis[1] -> "Tradisional"
                            jenis[2] -> "Modern"
                            else -> {jenisBatik}
                        }
                    )
                    viewModel.saveFilter(filterState)

                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 48.dp)
                    .navigationBarsPadding(),
                colors = ButtonDefaults.buttonColors(primary)
            ) {
                Text(
                    text = "Terapkan",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 8.dp)
                )
            }

            when(saveState){
                is UiState.Error -> {}
                UiState.Loading -> {}
                is UiState.Success -> {navController.navigate(Screen.Katalog.route){
                    popUpTo(Screen.Home.route)
                }}
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun Preview() {
    BatikPediaTheme {
        FilterContent(navController = rememberNavController())
    }
}