package com.nursena.jetpacktasarimcalismasi

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nursena.jetpacktasarimcalismasi.ui.theme.AnaRenk
import com.nursena.jetpacktasarimcalismasi.ui.theme.JetPackTasarimCalismasiTheme
import com.nursena.jetpacktasarimcalismasi.ui.theme.YaziRenk1
import com.nursena.jetpacktasarimcalismasi.ui.theme.YaziRenk2
import com.nursena.jetpacktasarimcalismasi.ui.theme.pacifico
import kotlin.coroutines.coroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackTasarimCalismasiTheme {


                PizzaPage()

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PizzaPage(){
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text( stringResource(id = R.string.pizzaBaslik), fontFamily = pacifico) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AnaRenk,
                    titleContentColor = YaziRenk1
                )
            )
        }
    )
    {
        paddingValues ->
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
    Text( stringResource(id = R.string.pizzaName), color = AnaRenk,
    fontWeight = FontWeight.Bold, fontSize = 36.sp)
          Image(painter = painterResource(id=R.drawable.pizza),
              contentDescription = "", Modifier.width(150.dp))
            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            )
            {
                //özelleştirilmiş widget
                Chip( stringResource(id = R.string.peynirYazi))
                Chip( stringResource(id = R.string.sucukYazi))
                Chip( stringResource(id = R.string.zeytinYazi))
                Chip( stringResource(id = R.string.biberYazi))

            }
            Text(
                stringResource(id = R.string.teslimatSure), color = YaziRenk1,
                fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Text( stringResource(id = R.string.teslimatBaslik), color = AnaRenk,
                fontWeight = FontWeight.Bold, fontSize = 22.sp)
            Text( stringResource(id = R.string.pizzaAciklama), color = YaziRenk1, fontSize = 22.sp,
                textAlign = TextAlign.Center)
Row (modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically)
{
    Text( stringResource(id = R.string.fiyat), color = AnaRenk,
        fontWeight = FontWeight.Bold, fontSize = 44.sp )
    Button(onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = YaziRenk1,
            containerColor = AnaRenk
        ),
        ) {
        Text( stringResource(id = R.string.buttonYazi), color = YaziRenk1,
            fontWeight = FontWeight.Bold, fontSize = 18.sp )
    }
}

        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackTasarimCalismasiTheme {
        PizzaPage()
    }
}