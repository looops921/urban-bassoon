package com.example.serfinalprojecttest.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serfinalprojecttest.R
import com.example.serfinalprojecttest.data.CharacterDao
import com.example.serfinalprojecttest.data.Character
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CharacterViewModel(private val characterDao: CharacterDao) : ViewModel() {
    val uniqueCharacters = characterDao.getAllCharacters()
        .map { characters -> characters.distinctBy { it.name } } // Removes duplicate names
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    fun addSampleCharacters() = viewModelScope.launch {
        val existingCharacters = characterDao.getAllCharacters().first()

        val characters = listOf(
            Character(
                name = "Invincible",
                basicBio = "Mark Grayson, son of Omni-Man",
                imageResId = R.drawable.invincible,
                powerRanking = 8,
                firstAppearance = "Invincible #1",
                loreText = "Mark Grayson is a teenage superhero..."
            ),
            Character(
                name = "Omni-Man",
                basicBio = "Nolan Grayson, Viltrumite warrior",
                imageResId = R.drawable.omniman,
                powerRanking = 10,
                firstAppearance = "Invincible #1",
                loreText = "Nolan Grayson is a Viltrumite..."
            ),
            Character(
                name = "Atom Eve",
                basicBio = "Samantha Eve Wilkins, matter manipulator",
                imageResId = R.drawable.atom_eve,
                powerRanking = 7,
                firstAppearance = "Invincible #5",
                loreText = "Atom Eve has the power..."
            )
        )
        // Only insert if the character isn't already in the database
        characters.forEach { character ->
            if (existingCharacters.none { it.name == character.name }) {
                characterDao.insert(character)

            }
        }
    }
}