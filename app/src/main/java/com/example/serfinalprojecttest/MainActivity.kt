package com.example.serfinalprojecttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.colorResource
import com.example.serfinalprojecttest.data.CharacterDatabase
import com.example.serfinalprojecttest.screens.HomeScreen
import com.example.serfinalprojecttest.ui.theme.SERFinalProjectTestTheme
import com.example.serfinalprojecttest.viewmodels.CharacterViewModel
import com.example.serfinalprojecttest.viewmodels.CharacterViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: CharacterViewModel by viewModels {
        CharacterViewModelFactory(
            CharacterDatabase.getDatabase(this).characterDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.addSampleCharacters()

        setContent {
            SERFinalProjectTestTheme(darkTheme = true) {
                val characters by viewModel.uniqueCharacters.collectAsState(initial = emptyList())

                HomeScreen(characters = characters)
            }
        }
    }
}
