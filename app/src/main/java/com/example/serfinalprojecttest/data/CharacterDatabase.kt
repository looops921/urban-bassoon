package com.example.serfinalprojecttest.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Character::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {
        fun getDatabase(context: Context): CharacterDatabase {
            return Room.databaseBuilder(
                context,
                CharacterDatabase::class.java,
                "Invincible_Database"
            ).build()
        }
    }
}