package com.dev.domain.usecase

import com.dev.domain.repository.QuestionRepository

class GetFinishAnswersUseCase(private val repository: QuestionRepository) {
    suspend fun invoke() = repository.getFinishAnswers()
}