package com.example.aulateste

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aulateste.ui.theme.AulaTesteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContent: bloco que define o layout do app a partir da funções
        setContent {
            //AulaTesteTheme ligado ao Material Desing
            AulaTesteTheme() {
                //Função para gerar várias mensagens a partir do arquivo SampleData e a variável conversationSample
                Conversation(SampleData.conversationSample)
            }
        }

    }
}
//aqui estamos declarando as variáveis que serão as propriedades de Message, author e body, ambas do tipo String
data class Message(val author: String, val body: String)

@Composable
fun Conversation(messages: List<Message>) {
    //LazyColumn/LazyRow - processam apensa elementos que aparecem na tela
    LazyColumn {
        messages.map { item { MessageCard(it) } }
    }
}

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

        /*
        para fazermos a animação de abrir o restante do conteúdo da mensagem, precisamos armazenar o estado da mensagem
        (se a mensagem está aberta ou não) na memória usando o remember, que pega o valor do estado através do mutableStateOf
        */
        var isExpanded by remember { mutableStateOf(false) }


        /*Aqui é uma coisa semelhante com o estado da mensagem. Mas agora, se uma mensagem foi aberta, a cor da mensagem
        vai mudar gradualmente da surface(branco) até a da primária(roxo), e ao ser fechada acontecerá o contrário*/
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )

        //A mensagem é clicável, e quando foi clicada, o conteúdo vai aparecer
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
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


            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                //A cor da surface cor vai se tornar gradualmente a cor primária citada acima
                color = surfaceColor,
                //animateContentSize vai mudar o tamanho da surface gradualmente, conforme as mensagens são abertas
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    /*se a mensagem não estiver aberta, só será mostrado 1 linha de conteúdo, caso contrário,
                    o número máximo de linhas serão todas as linhas da mensagem*/
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

//A partir daqui, temos só as pré-visualizações

//Notação que permite visualizar o resultado sem precisar emular o app (só pode ser usada com funções sem parâmetro)
@Preview
@Composable
fun PreviewConversation() {
    AulaTesteTheme() {
        /*Nessa pré-visualização, o esperado é que apareça uma lista de mensagens vinda do
        SampleData e que, usando a interatividade, essas mensagens mostrem as animações*/
        Conversation(SampleData.conversationSample)
    }
}