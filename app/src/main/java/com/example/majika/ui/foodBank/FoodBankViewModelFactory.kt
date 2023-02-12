package com.example.majika.ui.foodBank

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.majika.repository.Repository

class FoodBankViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FoodBankViewModel(repository) as T
    }
}