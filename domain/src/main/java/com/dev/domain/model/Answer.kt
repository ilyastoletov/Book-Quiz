package com.dev.domain.model

data class Answer(
    val questionNumber: Int,
    val quote: String,
    val bookImage: String,
    val correct: Boolean
)