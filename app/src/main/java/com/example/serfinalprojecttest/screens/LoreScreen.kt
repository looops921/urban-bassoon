package com.example.serfinalprojecttest.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.serfinalprojecttest.ViewModels.ThemeViewModel
import com.example.serfinalprojecttest.data.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoreScreen(
    character: Character,
    onBackClick: () -> Unit,
    themeViewModel: ThemeViewModel
) {
    val isDarkMode by themeViewModel.isDarkMode
    Column(
        modifier = Modifier.fillMaxSize().testTag("LoreScreenTag")
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "${character.name}'s Lore",
                            color = if (isDarkMode) Color.White else Color.Black
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick, Modifier.testTag("TopBarBackButtonTag")) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = if (isDarkMode) Color.White else Color.Black
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { themeViewModel.toggleDarkMode() }) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Toggle Dark Mode",
                                tint = if (isDarkMode) Color.White else Color.Black
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = if (isDarkMode) Color.Black else Color.White,
                        titleContentColor = if (isDarkMode) Color.White else Color.Black
                    )
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(if (isDarkMode) Color.Black else Color.White)
                    .padding(16.dp)
            ) {
                // Yellow-bordered container for the lore text
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 2.dp,
                            color = Color.Yellow,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp) // Padding inside the border
                ) {
                    Text(
                        text = character.loreText,
                        color = if (isDarkMode) Color.White else Color.Black,
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}