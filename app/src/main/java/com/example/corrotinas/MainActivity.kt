package com.example.corrotinas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.corrotinas.ui.theme.CorrotinasTheme
import kotlinx.coroutines.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CorrotinasTheme {
                Main()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CorrotinasTheme {
        Greeting("Android")
    }
}

//@Composable
//fun Luz(color: Color) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 8.dp),
//        horizontalArrangement = Arrangement.Center
//    ) {
//        Box(
//            modifier = Modifier
//                .background(color = color)
//                .padding(40.dp)
//        ) {
//            Text("")
//        }
//    }
//}


@Composable
fun Semaforo(c1: Color, c2: Color, c3: Color) {
    Column(
//        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Luz(c1)
        Luz(c2)
        Luz(c3)
    }
}

@Composable
fun Luz(color: Color) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(color, shape = CircleShape)
    )
}

@Composable
fun Main() {
    var c1 by remember { mutableStateOf(Color.Red) }
    var c2 by remember { mutableStateOf(Color.White) }
    var c3 by remember { mutableStateOf(Color.White) }
    var isYellowBlinking by remember { mutableStateOf(false) } // Controle do botão de piscar o amarelo

    LaunchedEffect(Unit) {
        while (true) {

            if(!isYellowBlinking) {
                // Vermelho
                c1 = Color.Red
                c2 = Color.White
                c3 = Color.White
                delay(2000L)
            }
            if (!isYellowBlinking) {
                // Verde
                c1 = Color.White
                c2 = Color.White
                c3 = Color.Green
                delay(2000L)
            }
            if (!isYellowBlinking) {
                // Amarelo
                c1 = Color.White
                c2 = Color.Yellow// Pisca amarelo se o botão estiver ativado
                c3 = Color.White
                delay(1000L) // Duração do amarelo
            }
            // Finaliza o ciclo
            if (isYellowBlinking) {
                // Volta para o estado normal (sem piscar)
                c1 = Color.White
                c2 = Color.Yellow
                c3 = Color.White
                delay(200L) // Duração do amarelo fixo
                c1 = Color.White
                c2 = Color.White
                c3 = Color.White
                delay(200L)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Semaforo(c1 = c1, c2 = c2, c3 = c3)

//        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            isYellowBlinking = !isYellowBlinking // Alterna o estado de piscar amarelo
        }) {
            Text(text = if (isYellowBlinking) "Parar Piscar Amarelo" else "Piscar Amarelo")
        }
    }
}