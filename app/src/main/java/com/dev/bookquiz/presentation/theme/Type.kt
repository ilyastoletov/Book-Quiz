package com.dev.bookquiz.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.dev.bookquiz.R
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontSize = 50.sp,
        lineHeight = 50.sp,
        fontFamily = FontFamily(Font(R.font.oswald_500)),
        fontWeight = FontWeight(500),
        color = Color(0xFF8BACFF),
        textAlign = TextAlign.Center,
    ),
    titleMedium = TextStyle(
        fontSize = 30.sp,
        lineHeight = 30.sp,
        fontFamily = FontFamily(Font(R.font.roboto_700)),
        fontWeight = FontWeight(700),
        color = Color(0xFFFFFFFF),
        textAlign = TextAlign.Center,
    )
)