package com.dev.bookquiz.presentation.screens.game.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.dev.bookquiz.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dev.bookquiz.presentation.ui.background.BackgroundImage
import com.dev.domain.model.Book
import com.dev.domain.model.Question


// «»
@Composable
fun GameScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BackgroundImage()
        GameContent()
    }
}

@Composable
private fun GameContent() {

    val questionNumber = remember { mutableIntStateOf(0) }
    val testQuestion = Question(
        quote = "«Знаете ли, мышление по сути своей нереально, это лишь нейронные импульсы, которым индивид предает контекст»",
        books = listOf(
            Book(imageUrl = "https://abrakadabra.fun/uploads/posts/2022-02/thumbs/1645979755_34-abrakadabra-fun-p-prestuplenie-i-nakazanie-oblozhka-knigi-63.jpg", isCorrect = true),
            Book(imageUrl = "https://avatars.mds.yandex.net/i?id=c481750aed7d4831fe190a1dfc58509b63e7def4-10350639-images-thumbs&n=13"),
            Book(imageUrl = "https://salonfifi.ru/wp-content/uploads/8/e/8/8e8c4a91390770bc21a2869da85cf159.jpeg")
        )
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Question: ${questionNumber.value} of 10",
            color = Color(0xFF9f9f9f),
            fontFamily = FontFamily(Font(R.font.roboto_500)),
            fontSize = 16.sp,
        )
        Text(
            text = stringResource(id = R.string.what_book),
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.roboto_500)),
            fontSize = 18.sp
        )
        QuestionContent(testQuestion)
    }
}

@Composable
private fun QuestionContent(question: Question) {

    val isBookChosen = remember { mutableStateOf(false) }
    val onBookChosen: (Book) -> Unit = { book ->  }

    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(start = 15.dp, end = 15.dp, top = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = question.quote,
            fontFamily = FontFamily(Font(R.font.roboto_500_italic)),
            color = Color.Black,
            fontSize = 28.sp,
            textAlign = TextAlign.Center
        )
        LazyRow() {
            items(question.books) { BookItem(it, isBookChosen, onBookChosen) }
        }
    }
}

@Composable
private fun BookItem(book: Book, bookChosen: MutableState<Boolean>, onBookChosen: (Book) -> Unit) {

    val bookClicked = remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.Center) {
        if (bookClicked.value) {
            Image(
                painter = if (book.isCorrect) painterResource(id = R.drawable.baseline_done_24) else painterResource(id = R.drawable.baseline_close_24),
                contentDescription = null
            )
        }
        AsyncImage(
            modifier = Modifier
                .width(100.dp)
                .height(150.dp)
                .clickable {
                    if (!bookChosen.value) {
                        bookClicked.value = true
                        bookChosen.value = true
                        onBookChosen(book)
                    }
                },
            model = book.imageUrl,
            contentScale = ContentScale.Fit,
            contentDescription = null
        )
    }
}