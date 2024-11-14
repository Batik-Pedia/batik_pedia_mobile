package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tricakrawala.batikpedia.presentation.ui.theme.BatikPediaTheme
import com.tricakrawala.batikpedia.presentation.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.presentation.ui.theme.textColor

@Composable
fun NavbarHome(
    modifier: Modifier = Modifier,
    textContent: String,
    isShow: Boolean = true,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .padding(top = 8.dp)
    ) {
        Text(
            text = textContent,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable { onClick() }
        )
        if (isShow) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                tint = textColor,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { onClick() }
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    BatikPediaTheme {
        NavbarHome(textContent = "Jelajahi nusantara", onClick = {})
    }
}