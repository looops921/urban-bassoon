package com.example.serfinalprojecttest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import com.example.serfinalprojecttest.data.CharacterDatabase
import com.example.serfinalprojecttest.navigation.AppNavigation
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

        setContent {
            SERFinalProjectTestTheme(darkTheme = true) {
                // Initialize sample data
                LaunchedEffect(Unit) {
                    viewModel.addSampleCharacters()
                }

                AppNavigation(viewModel = viewModel)
            }
        }
    }
}