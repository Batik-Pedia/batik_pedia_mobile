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
fun KatalogItemRow(
    modifier: Modifier = Modifier,
    image: Int,
    motif: String,
    jenis: String,
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "gambar motif batik",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)

        )

        Text(
            text = motif,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 10.sp,
            color = textColor,
            modifier = Modifier
                .padding(4.dp)
        )

        Text(
            text = jenis,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            color = textColor,
            modifier = Modifier
                .padding(bottom = 8.dp, start = 4.dp, end = 4.dp)
        )

    }

}

@Preview(showBackground = true)
@Composable
private fun preview() {
    BatikPediaTheme {
        KatalogItemRow(
            image = R.drawable.batik1,
            motif = "Mega Mendung",
            jenis = "Batik Tradisional"
        )
    }
}