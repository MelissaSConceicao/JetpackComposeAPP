package com.example.aulateste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.aulateste.ui.theme.AulaTesteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContent: bloco que define o layout do app a partir da funções
        setContent {
            AulaTesteTheme {
                //chamada da função "Greeting", com o parâmetro "name" e valor "Android"
                Greeting("Android")
            }

        }
    }
}
//notação para tornar uma função composta(uma função chama a outra)
@Composable
//função Greeting com o parâmetro "name" tipo String
fun Greeting(name: String) {
    //função Text
    Text(text = "Hello $name!")
}

//Notação que permite visualizar o resultado sem precisar emular o app (só pode ser usada com funções sem parâmetro)
@Preview
@Composable
//essa função permite visualizar o resultado do mesmo código feito acima
fun DefaultPreview() {
    AulaTesteTheme {
        Greeting("Android")
    }
}