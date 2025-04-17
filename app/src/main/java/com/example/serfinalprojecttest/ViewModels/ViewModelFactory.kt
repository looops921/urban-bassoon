package com.example.serfinalprojecttest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.serfinalprojecttest.data.CharacterDao

//Needed for character DAOs to be processed correctly in ViewModel
class CharacterViewModelFactory(private val characterDao: CharacterDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(characterDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}