package com.dev.bookquiz.presentation.ui.background

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.dev.bookquiz.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BackgroundImage(height: Float) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(height)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background_vector),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Image(
            modifier = Modifier.align(Alignment.Center).padding(10.dp),
            painter = painterResource(id = R.drawable.book_background),
            contentDescription = null
        )
    }
}