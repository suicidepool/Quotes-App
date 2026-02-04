package com.oms.quotify.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuoteCard(
    modifier: Modifier = Modifier,
    quoteIcon: ImageVector,
    shareButtonIcon: ImageVector,
    quote: String,
    writer: String,
    onShare: () -> Unit

) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd
    ){
        Card(
            modifier = Modifier.padding(bottom = 24.dp)
                .fillMaxWidth(),
            onClick = {},
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                contentColor = Color.Black,
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(10.dp),
            border = BorderStroke(
                width = 2.dp,
                color = Color(0xFFECECEC)
            ),
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    imageVector = quoteIcon,
                    contentDescription = null,
                    modifier = Modifier.size(42.dp)
                )

                Text(
                    text = quote,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = writer,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black.copy(alpha = 0.7f)
                )
            }
        }
        IconButton(
            onClick = onShare,
            modifier = Modifier
                .padding(end = 20.dp),
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = Color.White,
                containerColor = Color.DarkGray
            ),
        ){
            Icon(
                imageVector = shareButtonIcon,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
            )
        }
    }

}