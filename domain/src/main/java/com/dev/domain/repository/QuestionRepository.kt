package com.dev.domain.repository

import com.dev.domain.model.Answer
import com.dev.domain.model.Question

interface QuestionRepository {
    suspend fun getQuestion(): Question
    suspend fun writeAnswer(answer: Answer)
    suspend fun getFinishAnswers(): List<Answer>
}