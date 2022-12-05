package com.example.aulateste

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aulateste.ui.theme.AulaTesteTheme
//Agora vamos usar o Material Desing no projeto, um conjunto de elementos de iu (interface do usuário) prontos para uso
//O Material Desing foi criado com base em cor, tipografia e forma
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContent: bloco que define o layout do app a partir da funções
        setContent {
            //tanto no setContent quanto no @Preview, as funções AulaTesteThem e Surface estão ligadas/herdeiras do Material Design
            AulaTesteTheme() {
                Surface(modifier = Modifier.fillMaxSize()) {
                    //Aqui estamos chamando a composição MessageCard que está chamando o objeto Message e dando o valor das propriedades author e body
                    MessageCard(Message("Android", "Jetpack Compose"))
                }
            }
        }

    }
}
//aqui estamos declarando as variáveis que serão as propriedades de Message, author e body, ambas do tipo String
data class Message(val author: String, val body: String)

//notação para tornar uma função composta(uma função chama a outra)
@Composable
//agora, a composição recebe um objeto Message ao invés de uma String
//Esse msg é a forma como vamos refenciar o objeto Message
fun MessageCard(msg: Message) {
    //função Text
    //Row: organiza elementos horizontalmente, em uma linha
    Row(modifier = Modifier.padding(all = 8.dp)) {
        //O primeiro elemento da linha é uma imagem do autor da mensagem
        Image(
            //O painter vai mostrar a imagem, e o painterResource mostra o local de onde essa imagem está
            painter = painterResource(R.drawable.profile),
            //aqui é uma descrição da imagem
            contentDescription = null,
            //aqui é uma modificação para a imagem, mudando o seu tamanho para 40 dp e deixando-a em um formato circular
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                    //Na modificação dessa borda, temos um uso do Material Desing, em forma da configuração de uma cor
                .border(1.5.dp, MaterialTheme.colors.primary, CircleShape)
        )
        //um espaço entre a imagem e os textos, de largura 8 dp
        Spacer(modifier = Modifier.width(8.dp))

        //O próximo elemento é uma coluna com dois textos: o nome do autor da mensagem e a mensagem
        Column {
            Text(
                //Nome do autor da mensagem
                text = msg.author,
                //Seleção de uma cor através do material Desing
                color = MaterialTheme.colors.secondary,
                //Seleção de uma tipografia através do material Desing
                style = MaterialTheme.typography.subtitle2
            )
            //Espaço vertical de 4 dp entre os dois textos
            Spacer(modifier = Modifier.height(4.dp))

            //Aqui temos uma configuração de forma com o Material Desing e a elevação (sombra) do elemento
            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp){
                Text(
                    //Mensagem
                    text = msg.body,
                    //Espaço em volta do conteúdo do elemento de 4 dp
                    modifier = Modifier.padding(all = 4.dp),
                    //Outra seleção de tipografia com Material Desing
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}

//Notação que permite visualizar o resultado sem precisar emular o app (só pode ser usada com funções sem parâmetro)
@Preview(name = "Light Mode")
@Preview(
    //Criação de um Moddo Escuro usando Material Desing, os textos e os planos de fundo são adaptados automaticamente
    //Essas opções de cores para temas claros ou escuros estão no arquivo theme.kt no diretório ui.theme, juntamente com as cores, formas e tipografia
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)

@Composable
fun PreviewMessageCard() {
    //Aqui na pré-visualização, o esperado é aparecer um card de mensagem com a imagem do perfil, o autor de nome "Lexi" e a sua mensagem
    AulaTesteTheme() {
        Surface{
            MessageCard(
                msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
            )
        }
    }
}