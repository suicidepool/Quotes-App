package com.oms.quotify.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Title(
    modifier: Modifier = Modifier.padding(top = 44.dp)
) {
    Text(
        modifier = modifier,
        text = "QUOTIFY",
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 32.sp
    )
}