package com.example.majika.ui.shoppingCart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.majika.repository.CartRepository

class ShoppingCartViewModelFactory(private val repository: CartRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingCartViewModel(repository) as T
    }
}