package com.dev.bookquiz.presentation.screens.results.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dev.bookquiz.R
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.dev.bookquiz.presentation.screens.results.contract.ResultsContract
import com.dev.bookquiz.presentation.screens.results.contract.ResultsViewModel
import com.dev.bookquiz.presentation.theme.Typography
import com.dev.bookquiz.presentation.ui.background.BackgroundImage
import com.dev.bookquiz.presentation.ui.button.OrangeButton
import com.dev.bookquiz.presentation.ui.loading.LoadingText
import com.dev.domain.model.Answer

@Composable
fun ResultsScreen(navController: NavController, viewModel: ResultsViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ResultsScreenContent(state, viewModel::handleEvents)

    LaunchedEffect(key1 = true) {
        viewModel.effect.collect { effect ->
            when(effect) {
                is ResultsContract.Effect.NavigateToMenu -> navController.navigate("startScreen")
            }
        }
    }

}

@Composable
private fun ResultsScreenContent(state: ResultsContract.State, onEvent: (ResultsContract.Event) -> Unit) {
    
    when(state) {
        is ResultsContract.State.Loading -> { LoadingText(); onEvent(ResultsContract.Event.GetAnswers) }
        is ResultsContract.State.GotAnswers -> Content(state.answers, onEvent)
    }
    
}

@Composable
private fun Content(answers: List<Answer>, onEvent: (ResultsContract.Event) -> Unit) {

    val correctAnswersCount = answers.filter { answer -> answer.correct }.size

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(10.dp),
        userScrollEnabled = true
    ) {
        item { MainContentColumn(correctAnswersCount, onEvent) }
        item { TableHeadings() }
        items(answers) {
            AnswerItem(answer = it)
            Spacer(modifier = Modifier
                .height(2.dp)
                .background(Color.Black))
        }
    }

}

@Composable
private fun MainContentColumn(correctAnswersCount: Int, onEvent: (ResultsContract.Event) -> Unit) {
    Column(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = " ✨ Congratulations! ✨\nYou have correctly solved $correctAnswersCount of 10\nquestions.",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.roboto_700)),
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(5.dp))
        Button(
            modifier = Modifier
                .width(250.dp)
                .height(45.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFD4D0A)),
            onClick = { onEvent(ResultsContract.Event.TurnToMainMenu) }
        ) {
            Text(
                text = "back to menu".uppercase(),
                style = Typography.titleSmall
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "This is your answers list:",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.roboto_500_italic)),
            fontSize = 18.sp,
            color = Color.DarkGray
        )
    }
}

@Composable
private fun TableHeadings() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = "Question")
        Text(text = "Quote")
        Text(text = "Book")
        Text(text = "Correct")
    }
}

@Composable
private fun AnswerItem(answer: Answer) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = answer.questionNumber.toString()
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            modifier = Modifier
                .width(80.dp)
                .height(80.dp),
            text = answer.quote.take(30) + "...",
            lineHeight = 13.sp,
            fontSize = 13.sp,
            textAlign = TextAlign.Center
        )
        AsyncImage(
            modifier = Modifier
                .width(70.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(10.dp)),
            model = answer.bookImage,
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .width(45.dp)
                .height(45.dp),
            painter = if (answer.correct) painterResource(id = R.drawable.baseline_done_24) else painterResource(id = R.drawable.baseline_close_24),
            contentDescription = null
        )
    }
}