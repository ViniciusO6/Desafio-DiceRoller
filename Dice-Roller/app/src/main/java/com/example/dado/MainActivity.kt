package com.example.dado

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dado.ui.theme.DadoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DadoTheme {
                DadoApp()
            }
            exibirToast(this, "")
        }
    }
}
@Composable
fun DadoApp() {
    DadoBotaoImagem(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}
@Composable
fun exibirToast(contexto: Context, mensagem: String) {
    Toast.makeText(contexto, mensagem, Toast.LENGTH_SHORT).show()
}
@Preview(showBackground = true)
@Composable
fun DadoBotaoImagem(modifier: Modifier = Modifier) {
    var r by remember { mutableStateOf(1) }
    val imageResource = when (r) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = r.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { r = (1..6).random() }) {
            Text(stringResource(R.string.rolar))
        }
        var texto by remember { mutableStateOf("") }
        var numero = texto.toIntOrNull()
        OutlinedTextField(
            value = texto,
            onValueChange = { texto = it},
            label = { Text("Digite o número sorteado") }
        )
        if (numero != null && numero == r) {
            exibirToast(contexto = LocalContext.current as ComponentActivity, mensagem = "Parabéns você venceu!!!")
        }
        else if(numero != null){
                exibirToast(contexto = LocalContext.current as ComponentActivity, mensagem = "Você perdeu!!!")
            }
        }
    }
