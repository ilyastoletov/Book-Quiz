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
import com.dev.bookquiz.presentation.ui.button.OrangeButton

@Composable
fun StartScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        BackgroundImage(height = 0.5f)
        Text(
            text = stringResource(id = R.string.start_heading).uppercase(),
            style = Typography.headlineLarge
        )
        OrangeButton(text = "play") { navController.navigate("game") }
    }
}