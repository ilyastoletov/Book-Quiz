package com.dev.data.storage.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(UserDbModel::class)
    suspend fun writeAnswerToDatabase(answer: UserDbModel)

    @Query("SELECT * FROM user")
    suspend fun getAllAnswers(): List<UserDbModel>

}