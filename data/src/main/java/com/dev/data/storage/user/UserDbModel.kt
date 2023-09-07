package com.dev.data.storage.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dev.domain.model.Answer

@Entity(tableName = "user")
data class UserDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val questionNumber: Int,
    val quote: String,
    val bookImage: String,
    val isCorrect: Boolean
)

fun Answer.toDbModel(): UserDbModel {
    return UserDbModel(
        questionNumber = this.questionNumber,
        quote = this.quote,
        bookImage = this.bookImage,
        isCorrect = this.correct
    )
}

fun List<UserDbModel>.toAnswers(): List<Answer> {
    return this.map {
        Answer(
            questionNumber = it.questionNumber,
            quote = it.quote,
            bookImage = it.bookImage,
            correct = it.isCorrect)
    }
}