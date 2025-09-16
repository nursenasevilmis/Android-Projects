package com.nursena.calculatorapp

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nursena.calculatorapp.ui.theme.Poppins
import com.nursena.calculatorapp.ui.theme.blue

@Composable
fun ActionButton(
    symbol: String,
    onClick: (String) -> Unit
) {
    FloatingActionButton(
        onClick = { onClick(symbol) },
        containerColor = Color.White
    ) {
        Text(
            text = symbol,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color = blue,
            fontSize = 25.sp
        )
    }
}

