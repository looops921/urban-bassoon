package com.example.serfinalprojecttest.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val basicBio: String,
    val imageResId: Int,
    val powerRanking: Int,
    val firstAppearance: String,
    val loreText: String
)