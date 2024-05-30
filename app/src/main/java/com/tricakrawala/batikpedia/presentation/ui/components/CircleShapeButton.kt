package com.tricakrawala.batikpedia.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tricakrawala.batikpedia.R

@Composable
fun CircleShapeButton(
    modifier: Modifier = Modifier,
    image: ImageVector,
    contentDesc : String,
    onClick : () -> Unit,
) {
    IconButton(onClick = {onClick()}, modifier = modifier.size(48.dp).background(color = Color.White, shape = CircleShape)) {
        Icon(imageVector = image, contentDescription = contentDesc)
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 1)
private fun preview() {
    CircleShapeButton(image = ImageVector.vectorResource(id = R.drawable.ic_camera), contentDesc = "", onClick = {})
}