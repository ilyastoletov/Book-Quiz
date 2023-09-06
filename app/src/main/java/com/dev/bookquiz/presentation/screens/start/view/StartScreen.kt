package com.dev.bookquiz.presentation.screens.start.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dev.bookquiz.R
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dev.bookquiz.presentation.theme.Typography
import com.dev.bookquiz.presentation.ui.background.BackgroundImage

@Composable
fun StartScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BackgroundImage()
        StartScreenContent(navController)
    }
}

@Composable
private fun StartScreenContent(navController: NavController) {
    Text(
        text = stringResource(id = R.string.start_heading).uppercase(),
        style = Typography.headlineLarge
    )
    Button(
        modifier = Modifier
            .width(300.dp)
            .height(65.dp),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFD4D0A)),
        onClick = { navController.navigate("game") }
    ) {
        Text(
            text = stringResource(id = R.string.play).uppercase(),
            style = Typography.titleMedium
        )
    }
}