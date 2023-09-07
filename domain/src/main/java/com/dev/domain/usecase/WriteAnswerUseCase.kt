package com.dev.domain.usecase

import com.dev.domain.model.Answer
import com.dev.domain.repository.QuestionRepository

class WriteAnswerUseCase(private val repository: QuestionRepository) {
    suspend fun invoke(answer: Answer) = repository.writeAnswer(answer)
}