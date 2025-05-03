package com.example.serfinalprojecttest

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.serfinalprojecttest.ViewModels.ThemeViewModel
import com.example.serfinalprojecttest.navigation.AppNavigation
import com.example.serfinalprojecttest.viewmodels.CharacterViewModel
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testNavigationToDetailScreen() {
        val fakeDAO = FakeCharacterDAO()
        val fakeViewModel = CharacterViewModel(fakeDAO)

        composeTestRule.setContent {
            AppNavigation(viewModel = fakeViewModel, themeViewModel = ThemeViewModel())
        }

        // Click a character in the home screen to go to DetailScreen
        composeTestRule.onNodeWithTag("CharacterItemTag").performClick()

        // Verify DetailScreen is displayed
        composeTestRule.onNodeWithTag("DetailScreenTag").assertExists()

        // Click button to navigate to LoreScreen
        composeTestRule.onNodeWithTag("ReadLoreButtonTag").performClick()

        // Verify LoreScreen is displayed
        composeTestRule.onNodeWithTag("LoreScreenTag").assertExists()

        // Perform back navigation
        composeTestRule.onNodeWithTag("TopBarBackButtonTag").performClick()

        // Verify we are back at DetailScreen
        composeTestRule.onNodeWithTag("DetailScreenTag").assertExists()
    }

}