package com.example.serfinalprojecttest

import com.example.serfinalprojecttest.data.Character
import com.example.serfinalprojecttest.data.CharacterDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeCharacterDAO : CharacterDao {
    private val fakeCharacters = mutableListOf(
        com.example.serfinalprojecttest.data.Character(
            id = 1,
            name = "Test Character 1",
            basicBio = "A legendary warrior with mysterious origins.",
            imageResId = R.drawable.invincible,
            powerRanking = 95,
            firstAppearance = "Issue #1",
            loreText = "Once thought to be lost in time, this warrior returns..."
        ),
        com.example.serfinalprojecttest.data.Character(
            id = 2,
            name = "Test Character 2",
            basicBio = "A cunning strategist known for tactical genius.",
            imageResId = R.drawable.omniman,
            powerRanking = 88,
            firstAppearance = "Issue #5",
            loreText = "In the shadows, this figure orchestrates great events..."
        )
    )

    override suspend fun insert(character: Character) {
        fakeCharacters.add(character)
    }

    override fun getAllCharacters(): Flow<List<com.example.serfinalprojecttest.data.Character>> = flow {
        emit(fakeCharacters.sortedBy { it.name })
    }

    override suspend fun deleteAllCharacters() {
        fakeCharacters.clear()
    }
}