package com.dev.data.storage.questions

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionsModel(
    @PrimaryKey
    val id: Int,
    val quote: String,
    val bookImageUrl: String
)