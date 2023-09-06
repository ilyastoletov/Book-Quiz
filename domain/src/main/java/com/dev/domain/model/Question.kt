package com.dev.domain.model

data class Question(
    val quote: String,
    val books: List<Book>
)