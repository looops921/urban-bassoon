package com.example.serfinalprojecttest.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serfinalprojecttest.R
import com.example.serfinalprojecttest.data.Character
import com.example.serfinalprojecttest.data.CharacterDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CharacterViewModel(private val characterDao: CharacterDao) : ViewModel() {
    // State for selected character (NEEDED for navigating screens with correct character info)
    var selectedCharacter by mutableStateOf<Character?>(null)
        private set

    // Flow of all characters from database
    private val _allCharacters = characterDao.getAllCharacters()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val allCharacters = _allCharacters

    // Flow of unique characters (distinct by name)
    val uniqueCharacters = _allCharacters
        .map { characters -> characters.distinctBy { it.name } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun selectCharacter(character: Character) {
        selectedCharacter = character
    }

    // Updated to clear existing data before inserting new sample characters, this keeps data up to date
    fun addSampleCharacters() = viewModelScope.launch {
        // Deletes all existing characters so the info is up to date
        characterDao.deleteAllCharacters()

        val sampleCharacters = listOf(
            Character(
                name = "Invincible",
                basicBio = "Mark Grayson, son of Omni-Man",
                imageResId = R.drawable.invincible,
                powerRanking = 8,
                firstAppearance = "Invincible #1",
                loreText = "Mark Grayson, better known as Invincible, is the half-human, half-Viltrumite son of Nolan Grayson (Omni-Man).\n\n" +
                        "His powers manifested at age 17, including super strength, flight, near-invulnerability, and rapid healing.\n\n" +
                        "Initially mentored by his father, Mark's world shattered when Omni-Man revealed his true Viltrumite mission " +
                        "to conquer Earth. After a brutal battle that nearly destroyed Chicago, Mark chose to protect Earth rather than " +
                        "join the Viltrumite Empire.\n\n" +
                        "Notable Facts:\n" +
                        "- Can lift over 100 tons at full power\n" +
                        "- Survived direct hits from planetary-level threats\n" +
                        "- Currently leads the reformed Guardians of the Globe"
            ),
            Character(
                name = "Omni-Man",
                basicBio = "Nolan Grayson, Viltrumite warrior",
                imageResId = R.drawable.omniman,
                powerRanking = 10,
                firstAppearance = "Invincible #1",
                loreText = "Nolan Grayson, the Viltrumite warrior known as Omni-Man, arrived on Earth posing as a superhero while " +
                        "secretly preparing the planet for Viltrumite conquest.\n\n" +
                        "His true nature emerged when he brutally murdered the Guardians of the Globe. The subsequent battle with his son " +
                        "Mark left cities in ruins.\n\n" +
                        "Powers:\n" +
                        "- Class 100+ strength (can bench press small planets)\n" +
                        "- Hypersonic flight (can reach other star systems)\n" +
                        "- 500+ years of battle experience"
            ),
            Character(
                name = "Atom Eve",
                basicBio = "Samantha Eve Wilkins, matter manipulator",
                imageResId = R.drawable.atom_eve,
                powerRanking = 7,
                firstAppearance = "Invincible #5",
                loreText = "Samantha Eve Wilkins, codenamed Atom Eve, possesses matter manipulation abilities allowing her to rearrange " +
                        "atomic structures of non-living materials.\n\n" +
                        "Key Moments:\n" +
                        "- Created food to end world hunger (temporarily)\n" +
                        "- Rewrote her own brain programming\n" +
                        "- Became mother to Terra Grayson\n\n" +
                        "Weaknesses:\n" +
                        "- Cannot affect organic matter (originally)\n" +
                        "- Power strain causes severe nosebleeds"
            )
        )

        sampleCharacters.forEach { character ->
            characterDao.insert(character)
        }
    }
}