package com.example.serfinalprojecttest.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.serfinalprojecttest.R
import com.example.serfinalprojecttest.data.Character

@Composable
fun HomeScreen(
    characters: List<Character>,
    onCharacterClick: (Character) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.invinciblelogo),
            contentDescription = "Invincible Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(16.dp),
            contentScale = ContentScale.Fit
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp), // Increased horizontal padding
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(characters) { character ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    // Hero image with rounded square border
                    Image(
                        painter = painterResource(id = character.imageResId),
                        contentDescription = "${character.name} image",
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(12.dp)) //Rounded Square shape
                            .border(
                                width = 2.dp,
                                color = Color.Yellow,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(4.dp), // Small internal padding
                        contentScale = ContentScale.Crop
                    )

                    Spacer(Modifier.width(16.dp))

                    CharacterPill(
                        character = character,
                        onInfoClick = { onCharacterClick(character) }
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterPill(
    character: Character,
    onInfoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(70.dp)
            .fillMaxWidth()
            .background(Color.Yellow, RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(50.dp)
            )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Character name
            Text(
                text = character.name,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 24.dp),
                maxLines = 1
            )

            // Info button
            Button(
                onClick = onInfoClick,
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6EC6FF),
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp)
                    .padding(end = 8.dp)
            ) {
                Text("INFO", fontSize = 14.sp)
            }
        }
    }
}