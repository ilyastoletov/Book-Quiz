package com.dev.data.storage.questions

import androidx.room.Dao
import androidx.room.Query
import kotlin.random.Random

@Dao
interface QuestionDao {

    @Query("SELECT * FROM questions WHERE id = :id")
    suspend fun getQuestion(id: Int): QuestionsModel

    @Query("SELECT * FROM questions")
    suspend fun getAllQuestions(): List<QuestionsModel>

}