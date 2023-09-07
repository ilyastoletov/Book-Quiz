package com.dev.data.storage.user

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserDbModel::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

}