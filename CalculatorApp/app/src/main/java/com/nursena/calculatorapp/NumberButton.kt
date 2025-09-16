package com.nursena.calculatorapp



import android.R.attr.fontFamily
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nursena.calculatorapp.ui.theme.Poppins
import com.nursena.calculatorapp.ui.theme.blue

@Composable
fun NumberButton (input: String,
            onClick: (String) -> Unit){
    FloatingActionButton(
        onClick = {onClick(input)},
        containerColor = Color.White,


        ) {
        Text(input,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            color  = Color.Black,
            fontSize = 25.sp)


    }
}