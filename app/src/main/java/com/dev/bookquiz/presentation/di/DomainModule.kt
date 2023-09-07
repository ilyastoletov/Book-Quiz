package com.dev.bookquiz.presentation.di

import com.dev.domain.repository.QuestionRepository
import com.dev.domain.usecase.GetQuestionUseCase
import com.dev.domain.usecase.WriteAnswerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class DomainModule {

    @Provides
    fun provideGetQuestionUseCase(questionRepository: QuestionRepository): GetQuestionUseCase {
        return GetQuestionUseCase(questionRepository)
    }

    @Provides
    fun provideWriteAnswerUseCase(questionRepository: QuestionRepository): WriteAnswerUseCase {
        return WriteAnswerUseCase(questionRepository)
    }

}