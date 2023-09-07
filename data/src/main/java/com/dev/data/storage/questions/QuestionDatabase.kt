package com.dev.data.storage.questions

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuestionsModel::class], version = 1)
abstract class QuestionDatabase : RoomDatabase() {

    abstract fun getQuestionsDao(): QuestionDao

}