package com.example.serfinalprojecttest.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.serfinalprojecttest.ViewModels.ThemeViewModel
import com.example.serfinalprojecttest.data.Character

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    character: Character,
    onBackClick: () -> Unit,
    onReadLoreClick: () -> Unit,
    themeViewModel: ThemeViewModel
) {
    val isDarkMode by themeViewModel.isDarkMode
    Column(
        modifier = Modifier.fillMaxSize().testTag("DetailScreenTag")
    ) {
        Scaffold(
            topBar = {
                var showMenu by remember { mutableStateOf(false) }
                TopAppBar(
                    title = {
                        Text(
                            character.name,
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
                        IconButton(onClick = { showMenu = true }) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Settings",
                                tint = if (isDarkMode) Color.White else Color.Black
                            )
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                onClick = {
                                    themeViewModel.toggleDarkMode() // Toggle Dark Mode
                                    showMenu = false
                                },
                                text = {
                                    Text(
                                        text = if (isDarkMode) "Disable Dark Mode" else "Enable Dark Mode",
                                        color = if (isDarkMode) Color.White else Color.Black
                                    )
                                }
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(if (isDarkMode) Color.Black else Color.White)
                    .verticalScroll(rememberScrollState())
            ) {
                // Character Image
                Image(
                    painter = painterResource(id = character.imageResId),
                    contentDescription = "${character.name} image",
                    modifier = Modifier
                        .size(500.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                        .clip(MaterialTheme.shapes.large)
                        .border(4.dp, Color.Yellow, MaterialTheme.shapes.large),
                    contentScale = ContentScale.Crop
                )

                // Character Stats
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    // Power Ranking Bar
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        Text(
                            text = "Power Ranking:",
                            color = if (isDarkMode) Color.White else Color.Black,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        PowerRatingBar(
                            rating = character.powerRanking,
                            maxRating = 10,
                            activeColor = Color.Yellow,
                            inactiveColor = Color.Gray.copy(alpha = 0.5f),
                            modifier = Modifier.height(24.dp)
                        )

                        Text(
                            text = "${character.powerRanking}/10",
                            color = Color.Yellow,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }

                // Read Lore Button
                Button(
                    onClick = onReadLoreClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .height(60.dp)
                        .testTag("ReadLoreButtonTag"),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Yellow,
                        contentColor = Color.Black
                    ),
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(
                        text = "READ LORE",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
@Composable
fun PowerRatingBar( // Visual bar
    rating: Int,
    maxRating: Int,
    activeColor: Color,
    inactiveColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(maxRating) { index -> //Each box in the row gets an index number, if the index is less than their rating, it'll be yellow.
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(
                        if (index < rating) activeColor else inactiveColor,
                        RoundedCornerShape(4.dp)
                    )
            )
        }
    }
}