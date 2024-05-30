package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor

@Composable
fun ProvinsiItemRow(
    modifier: Modifier = Modifier,
    image: Int,
    provinsi: String
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "gambar provinsi",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(140.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Text(
            text = provinsi,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = textColor,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview(){
    BatikPediaTheme {
        ProvinsiItemRow(image = R.drawable.yogyakarta, provinsi = "Yogyakarta")
    }
}