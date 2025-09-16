package com.nursena.calculatorapp

import com.nursena.calculatorapp.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nursena.calculatorapp.ui.theme.Poppins
import com.nursena.calculatorapp.ui.theme.lightGrey
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator() {
    var inputNum = remember { mutableStateOf("") }
    var storedNum by remember { mutableStateOf(0) }  // Önceki sayı
    var selectedOp by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    val history = remember { mutableStateListOf<String>() }

    fun evaluateExpression(expression: String): Double {
        if (expression.isBlank()) return 0.0

        val numbers = mutableListOf<Double>()
        val operators = mutableListOf<Char>()

        var i = 0
        while (i < expression.length) {
            var ch = expression[i]

            // Sayı veya ondalık
            if (ch.isDigit() || ch == '.') {
                var numStr = ""
                while (i < expression.length && (expression[i].isDigit() || expression[i] == '.')) {
                    numStr += expression[i]
                    i++
                }
                numbers.add(numStr.toDouble())
                continue
            }

            // "-" işareti negatif sayı mı yoksa çıkarma mı
            if (ch == '-' && (i == 0 || expression[i-1] in "+-*/")) {
                // Negatif sayı
                var numStr = "-"
                i++
                while (i < expression.length && (expression[i].isDigit() || expression[i] == '.')) {
                    numStr += expression[i]
                    i++
                }
                numbers.add(numStr.toDouble())
                continue
            }

            // Operatörleri kaydet
            if (ch in "+*/-") {
                operators.add(ch)
            }

            i++
        }

        // Çarpma ve bölme önce
        var j = 0
        while (j < operators.size) {
            if (operators[j] == '*' || operators[j] == '/') {
                val res = if (operators[j] == '*') numbers[j] * numbers[j+1] else numbers[j] / numbers[j+1]
                numbers[j] = res
                numbers.removeAt(j+1)
                operators.removeAt(j)
            } else j++
        }

        // Toplama ve çıkarma
        var result = numbers[0]
        for (k in operators.indices) {
            result = when (operators[k]) {
                '+' -> result + numbers[k+1]
                '-' -> result - numbers[k+1]
                else -> result
            }
        }

        return result
    }



    fun onButtonClick(value: String) {
        when (value) {
            "C" -> {
                inputNum.value = ""
                result = ""
            }
            "=" -> {
                try {
                    val hesapSonucu = evaluateExpression(inputNum.value)
                    result = hesapSonucu.toString()
                } catch (e: Exception) {
                    result = "Hata"
                }
            }
            else -> inputNum.value += value
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculator", fontFamily = Poppins,
                    fontWeight = FontWeight.Bold

                )
                        },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues),

        ) {
            // Üst yarı: input ve results
            Box(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom
                ) {

                    Text(
                        text = inputNum.value,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 40.sp,
                        color = Color.Black
                    )
                    // Hesap sonucu
                    Text(
                        text = result,
                        fontSize = 55.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 1, // Tek satır
                        softWrap = false, // Sarmayı kapat
                        overflow = TextOverflow.Ellipsis // Taşma olursa .. göster
                    )
                }
            }


            // Alt yarı: butonlar
            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
             Column (Modifier
                 .fillMaxSize()
                 .background(lightGrey),
                 horizontalAlignment = Alignment.End,
                 verticalArrangement = Arrangement.Bottom){
                 Row (Modifier.fillMaxWidth()

                     .padding(10.dp),
                     horizontalArrangement = Arrangement.SpaceEvenly){

                     ActionButton("C") { value ->
                         inputNum.value = ""
                         result = ""
                     }
                     ActionButton("+/-") {
                         if (inputNum.value.isNotEmpty()) {
                             // Son sayıyı bul
                             val regex = Regex("(-?\\d+\\.?\\d*)$")
                             val match = regex.find(inputNum.value)
                             if (match != null) {
                                 val number = match.value
                                 val toggled = if (number.startsWith("-")) number.removePrefix("-") else "-$number"
                                 inputNum.value = inputNum.value.dropLast(number.length) + toggled
                             }
                         }
                     }
                     ActionButton("%"){
                         if (inputNum.value.isNotEmpty()) {
                             try {
                                 val currentNumber = inputNum.value.toDouble()
                                 val percent = currentNumber / 100
                                 inputNum.value = percent.toString()
                             } catch (e: Exception) {
                                 // Hata varsa bir şey yapma
                             }
                         }
                     }
                     ActionButton("÷"){ value ->   if (result.isNotEmpty()) {
                         inputNum.value = result + "/"
                         result = ""
                     } else {
                         inputNum.value += "/"
                     }  }
                 }
                 //***
                 Row (Modifier.fillMaxWidth()
                     .padding(10.dp),
                     horizontalArrangement = Arrangement.SpaceEvenly){

                     NumberButton("7"){ value ->
                         if (result.isNotEmpty()) {
                             result = ""
                             inputNum.value= value// input doluysa temizle
                         } else {
                             inputNum.value += value // değilse sayıyı ekle
                         } }
                     NumberButton("8") { value ->
                         if (result.isNotEmpty()) {
                             result = ""
                             inputNum.value= value// input doluysa temizle
                         } else {
                             inputNum.value += value // değilse sayıyı ekle
                         } }
                     NumberButton("9"){ value ->
                         if (result.isNotEmpty()) {
                             result = ""
                             inputNum.value= value// input doluysa temizle
                         } else {
                             inputNum.value += value // değilse sayıyı ekle
                         } }
                     ActionButton("×"){ value ->  if (result.isNotEmpty()) {
                         inputNum.value = result + "*"
                         result = ""
                     } else {
                         inputNum.value += "*"
                     }  }
                 }

                 Row (Modifier.fillMaxWidth()
                     .padding(10.dp),
                     horizontalArrangement = Arrangement.SpaceEvenly){

                     NumberButton("4"){value ->
                         if (result.isNotEmpty()) {
                             result = ""
                             inputNum.value= value// input doluysa temizle
                         } else {
                             inputNum.value += value // değilse sayıyı ekle
                         } }
                     NumberButton("5"){ value ->
                         if (result.isNotEmpty()) {
                             result = ""
                             inputNum.value= value// input doluysa temizle
                         } else {
                             inputNum.value += value // değilse sayıyı ekle
                         }}
                     NumberButton("6"){ value ->
                         if (result.isNotEmpty()) {
                             result = ""
                             inputNum.value= value// input doluysa temizle
                         } else {
                             inputNum.value += value // değilse sayıyı ekle
                         } }
                     ActionButton("-") { value ->
                         if (result.isNotEmpty()) {
                             inputNum.value = result + value
                             result = ""
                         } else {
                             inputNum.value += value
                         }
                     }
                 }
                 Row (Modifier.fillMaxWidth()
                     .padding(10.dp),
                     horizontalArrangement = Arrangement.SpaceEvenly){

                     NumberButton("1"){ value ->
                         if (result.isNotEmpty()) {
                             result = ""
                             inputNum.value= value// input doluysa temizle
                         } else {
                             inputNum.value += value // değilse sayıyı ekle
                         } }
                     NumberButton("2"){ value ->
                         if (result.isNotEmpty()) {
                             result = ""
                             inputNum.value= value// input doluysa temizle
                         } else {
                             inputNum.value += value // değilse sayıyı ekle
                         } }
                     NumberButton("3"){ value ->
                         if (result.isNotEmpty()) {
                             result = ""
                         inputNum.value= value// input doluysa temizle
                         } else {
                             inputNum.value += value // değilse sayıyı ekle
                         }
                     }
                     ActionButton("+"){ value ->   if (result.isNotEmpty()) {
                         inputNum.value = result + value
                         result = ""
                     } else {
                         inputNum.value += value
                     }  }
                 }
                 Row (Modifier.fillMaxWidth()
                     .padding(10.dp),
                     horizontalArrangement = Arrangement.SpaceEvenly){

                     NumberButton("0"){ value ->
                         if (result.isNotEmpty()) {
                             result = ""
                             inputNum.value= value// input doluysa temizle
                         } else {
                             inputNum.value += value // değilse sayıyı ekle
                         } }
                     NumberButton("."){ value -> inputNum.value += value }
                     BButton("",{
                         if (inputNum.value.isNotEmpty()) inputNum.value = inputNum.value.dropLast(1)
                     },iconRes = R.drawable.backspace)
                     ActionButton("=") { value ->
                         try {
                             val hesapSonucu = evaluateExpression(inputNum.value)
                             result = hesapSonucu.toString()
                             history.add("${inputNum.value} = $result")
                         } catch (e: Exception) {
                             result = "Hata"
                         }
                     }
                 }

             }
            }
        }
    }
}

