package com.dev.bookquiz.presentation.screens.game.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import com.dev.bookquiz.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.dev.bookquiz.presentation.screens.game.contract.GameContract
import com.dev.bookquiz.presentation.screens.game.contract.GameViewModel
import com.dev.bookquiz.presentation.ui.background.BackgroundImage
import com.dev.bookquiz.presentation.ui.button.OrangeButton
import com.dev.domain.model.Answer
import com.dev.domain.model.Book
import com.dev.domain.model.Question


// «»
@Composable
fun GameScreen(viewModel: GameViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BackgroundImage(height = 0.3f)
        GameContent(state = state, onEvent= viewModel::handleEvents)
    }
}

@Composable
private fun GameContent(state: GameContract.State, onEvent: (GameContract.Event) -> Unit) {

    when(state) {
        is GameContract.State.Pending -> { LoadingText(); onEvent(GameContract.Event.GetQuestion) }
        is GameContract.State.ReceivedQuestion -> Content(state.data, onEvent)
    }

}

@Composable
private fun Content(question: Question, onEvent: (GameContract.Event) -> Unit) {

    val questionNumber = remember { mutableIntStateOf(0) }

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
        QuestionContent(question, questionNumber, onEvent)
    }
}

@Composable
private fun LoadingText() {
    Text(
        text = stringResource(id = R.string.loading),
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.roboto_400))
    )
}

@Composable
private fun QuestionContent(
    question: Question,
    questionNumber: MutableState<Int>,
    onEvent: (GameContract.Event) -> Unit) {

    val isBookChosen = remember { mutableStateOf(false) }

    val chosenBook = remember { mutableStateOf<Book?>(null) }
    val onBookClicked: (Book) -> Unit = { chosenBook.value = it }

    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(start = 15.dp, end = 15.dp, top = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = question.quote,
            fontFamily = FontFamily(Font(R.font.roboto_500_italic)),
            color = Color.Black,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        LazyRow {
            items(question.books) { BookItem(it, isBookChosen, onBookClicked) }
        }
        if (isBookChosen.value) {
            OrangeButton(text = "next") {
                isBookChosen.value = false
                questionNumber.value += 1
                onEvent(GameContract.Event.WriteAnswer(
                    Answer(
                        questionNumber = questionNumber.value,
                        quote = question.quote,
                        bookImage = question.books.find { book -> book.isCorrect }!!.imageUrl,
                        correct = chosenBook.value?.isCorrect ?: false
                        )
                    )
                )
                onEvent(GameContract.Event.GetQuestion)
            }
        }
    }
}

@Composable
private fun BookItem(book: Book, bookChosen: MutableState<Boolean>, onBookClicked: (Book) -> Unit) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .width(100.dp)
                .height(150.dp)
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .clickable {
                        if (!bookChosen.value) {
                            bookChosen.value = true
                            onBookClicked(book)
                        }
                    },
                model = book.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )
        }
        if (bookChosen.value) {
            Image(
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp),
                painter = if (book.isCorrect) painterResource(id = R.drawable.baseline_done_24) else painterResource(id = R.drawable.baseline_close_24),
                contentDescription = null
            )
        }
    }
}