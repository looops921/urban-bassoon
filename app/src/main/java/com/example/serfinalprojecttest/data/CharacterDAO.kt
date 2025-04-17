package com.example.serfinalprojecttest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(character: Character)

    @Query("SELECT * FROM characters ORDER BY name ASC")
    fun getAllCharacters(): Flow<List<Character>>
}