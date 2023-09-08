package com.dev.bookquiz.presentation.ui.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dev.bookquiz.R
import com.dev.bookquiz.presentation.theme.Typography

@Composable
fun OrangeButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.width(300.dp).height(65.dp),
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFD4D0A)),
        onClick = { onClick() }
    ) {
        Text(
            text = text.uppercase(),
            style = Typography.titleMedium
        )
    }
}