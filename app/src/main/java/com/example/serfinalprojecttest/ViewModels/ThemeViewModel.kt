package com.example.serfinalprojecttest.ViewModels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class ThemeViewModel : ViewModel() {
    private val _isDarkMode = mutableStateOf(false) // Dark Mode state
    val isDarkMode: State<Boolean> = _isDarkMode

    // Method to toggle Dark Mode
    fun toggleDarkMode() {
        _isDarkMode.value = !_isDarkMode.value
    }
}