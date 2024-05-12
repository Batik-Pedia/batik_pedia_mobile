package com.tricakrawala.batikpedia.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tricakrawala.batikpedia.R
import com.tricakrawala.batikpedia.ui.theme.background2
import com.tricakrawala.batikpedia.ui.theme.poppinsFontFamily
import com.tricakrawala.batikpedia.ui.theme.primary
import com.tricakrawala.batikpedia.ui.theme.textColor

@Composable
fun PopupFilter(
    items: List<String>,
    selectedItems: List<Boolean>,
    onDismissRequest: () -> Unit,
    onItemSelected: (Int) -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        backgroundColor = background2,
        shape = RoundedCornerShape(16.dp),
        buttons = {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.wilayah),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = textColor,
                    modifier = Modifier.padding(start = 16.dp)
                )
                items.forEachIndexed { index, item ->
                    Row(
                        modifier = Modifier.fillMaxWidth().clickable { onItemSelected(index) }
                    ) {
                        IconToggleButton(
                            checked = selectedItems[index],
                            onCheckedChange = {
                                onItemSelected(index)
                            },
                        ) {
                            if (selectedItems[index]) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_checked),
                                    contentDescription = "Checkbox icon",
                                    tint = primary
                                )
                            } else {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_check),
                                    contentDescription = "Checkbox icon",
                                    tint = primary
                                )
                            }
                        }
                        Text(
                            text = item,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            color = textColor,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }

        }
    )
}



