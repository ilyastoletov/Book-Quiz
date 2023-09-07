package com.dev.data.di

import android.content.Context
import androidx.room.Room
import com.dev.data.repository.QuestionRepoImpl
import com.dev.data.storage.questions.QuestionDao
import com.dev.data.storage.questions.QuestionDatabase
import com.dev.data.storage.user.UserDao
import com.dev.data.storage.user.UserDatabase
import com.dev.domain.repository.QuestionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideQuestionsDatabase(@ApplicationContext context: Context): QuestionDatabase = Room.databaseBuilder(context, QuestionDatabase::class.java, "questions_internal.db")
        .createFromAsset("questions.db")
        .build()

    @Provides
    @Singleton
    fun provideQuestionsDao(questionDatabase: QuestionDatabase): QuestionDao = questionDatabase.getQuestionsDao()

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context): UserDatabase = Room.databaseBuilder(context, UserDatabase::class.java, "user.db").fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideUserDao(userDatabase: UserDatabase): UserDao = userDatabase.getUserDao()

    @Provides
    @Singleton
    fun provideQuestionsRepository(questionsDao: QuestionDao, userDao: UserDao): QuestionRepository = QuestionRepoImpl(questionsDao, userDao)

}