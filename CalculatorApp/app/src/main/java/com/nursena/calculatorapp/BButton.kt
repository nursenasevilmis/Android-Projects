package com.nursena.calculatorapp

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nursena.calculatorapp.ui.theme.blue
@Composable
fun BButton(
    input: String,
    onClick: (String) -> Unit,
    iconRes: Int? = null // Drawable resource ID
) {
    FloatingActionButton(
        onClick = { onClick(input) },
        containerColor = blue,
        modifier = Modifier.size(60.dp)
    ) {
        if (iconRes != null) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = input,
                tint = Color.White
            )
        } else {
            Text(
                text = input,
                color = Color.White,
                fontSize = 25.sp
            )
        }
    }
}
