package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily

@Composable
fun NusantaraItemRow(
    modifier: Modifier = Modifier,
    provinsi: String,
    image: Int,
) {
    Box(
        modifier = modifier
            .width(200.dp)
            .height(115.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "provinsi",
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
        )

        Text(
            text = provinsi,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.TopCenter)
        )

    }

}

@Composable
@Preview(showBackground = true)
private fun preview() {
    BatikPediaTheme {
        NusantaraItemRow(provinsi = "Yogyakarta", image = R.drawable.yogyakarta)
    }
}