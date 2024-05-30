package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.primary

@Composable
fun ButtonNextSplah(
    modifier: Modifier = Modifier,
    color: Color,
    text: String,
    textColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color)
            .height(42.dp)
            .width(160.dp)
            .clickable { onClick() }
    ) {

        Text(
            modifier = Modifier.fillMaxWidth().align(Alignment.Center),
            text = text,
            color = textColor,
            textAlign = TextAlign.Center,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    BatikPediaTheme {
        ButtonNextSplah(color = primary, text = "Coba", textColor = Color.White, onClick = {})
    }
}