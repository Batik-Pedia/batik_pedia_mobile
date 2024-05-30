package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.primary

@Composable
fun ButtonLongGray(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(
        modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = primary)
            .height(45.dp)
            .fillMaxWidth()) {
        Text(
            text = text,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            fontSize = 14.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }

}

@Composable
@Preview(showBackground = true)
private fun preview() {
    ButtonLongGray(text = "Identifikasi")
}