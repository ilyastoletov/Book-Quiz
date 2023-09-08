package com.dev.bookquiz.presentation.ui.loading

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.dev.bookquiz.R

@Composable
fun LoadingText() {
    Text(
        text = stringResource(id = R.string.loading),
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.roboto_400))
    )
}