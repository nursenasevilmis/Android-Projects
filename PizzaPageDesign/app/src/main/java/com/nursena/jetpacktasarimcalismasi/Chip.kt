package com.nursena.jetpacktasarimcalismasi

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nursena.jetpacktasarimcalismasi.ui.theme.AnaRenk
import com.nursena.jetpacktasarimcalismasi.ui.theme.YaziRenk1


@Composable
fun Chip(btnText : String){
    Button(onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = AnaRenk,
            contentColor = YaziRenk1
        )) {
        Text(btnText)
    }
}
