package com.dev.bookquiz.presentation.screens.game.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.dev.bookquiz.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.bookquiz.presentation.ui.background.BackgroundImage
import com.dev.domain.model.Question

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
    val bookChosen = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
        QuestionContent(question = Question(quote = "'I am just tired of everything...'", books = listOf()))
    }
}

@Composable
private fun QuestionContent(question: Question) {
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
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
    }
}