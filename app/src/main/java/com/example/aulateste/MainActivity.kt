package com.example.aulateste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContent: bloco que define o layout do app a partir da funções
        setContent {
            //Aqui estamos chamando a composição MessageCard que está chamando o objeto Message e dando o valor das propriedades author e body
            MessageCard(Message("Android", "Jetpack Compose"))
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
            contentDescription = "Contact profile picture",
            //aqui é uma modificação para a imagem, mudando o seu tamanho para 40 dp e deixando-a em um formato circular
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        //um espaço entre a imagem e os textos, de largura 8 dp
        Spacer(modifier = Modifier.width(8.dp))

        //O próximo elemento é uma coluna com dois textos: o nome do autor da mensagem e a mensagem
        Column {
            //Nome do autor da mensagem
            Text(text = msg.author)
            //Espaço vertical de 4 dp entre os dois textos
            Spacer(modifier = Modifier.height(4.dp))
            //Mensagem
            Text(text = msg.body)
        }
    }
}

//Notação que permite visualizar o resultado sem precisar emular o app (só pode ser usada com funções sem parâmetro)
@Preview
@Composable
fun PreviewMessageCard() {
    //Aqui na pré-visualização, o esperado é aparecer um card de mensagem com a imagem do perfil, o autor de nome "Lexi" e a sua mensagem
    MessageCard(
        msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
    )

}