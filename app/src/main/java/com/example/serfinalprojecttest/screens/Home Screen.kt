package com.example.serfinalprojecttest.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap.Companion.Square
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.serfinalprojecttest.R
import com.example.serfinalprojecttest.data.Character
import com.example.serfinalprojecttest.ui.theme.SERFinalProjectTestTheme


@Composable
fun HomeScreen(
    characters: List<Character>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .fillMaxSize()
        .background(Color.Black)
        ) {
        // Invincible logo
        Image(
            painter = painterResource(id = R.drawable.invinciblelogo),
            contentDescription = "Invincible Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(16.dp),
            contentScale = ContentScale.Fit
        )

        // Character list
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(characters) { character ->
                CharacterCard(character = character)
            }
        }
    }
}

@Composable
fun CharacterCard(character: Character) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.Yellow),

        verticalAlignment = Alignment.CenterVertically
    ) {
        // Cyan Info Button (left side)
        Button(
            onClick = { /* TODO */ },
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6EC6FF),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .size(100.dp)
                .padding(8.dp)
                .border(width = 4.dp,
                        color = Color.White,

                )
        ) {
            Text("Info", color = Color.White)
        }

        // Yellow Rectangle for Names
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.Yellow)
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = character.name,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
        }

        // Circular Character Image (right side)
        Image(
            painter = painterResource(id = character.imageResId),
            contentDescription = "${character.name} image",
            modifier = Modifier
                .size(80.dp)
                .background(Color.Black)
                .clip(CircleShape),
            contentScale = ContentScale.Fit
        )
    }
}