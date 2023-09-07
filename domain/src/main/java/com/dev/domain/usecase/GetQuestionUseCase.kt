package com.dev.domain.usecase

import com.dev.domain.repository.QuestionRepository

class GetQuestionUseCase(private val repository: QuestionRepository) {
    suspend fun invoke() = repository.getQuestion()
}