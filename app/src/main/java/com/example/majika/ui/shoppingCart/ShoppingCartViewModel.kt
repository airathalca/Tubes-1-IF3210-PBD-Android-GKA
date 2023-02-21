package com.example.majika.ui.shoppingCart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.majika.repository.CartRepository
import com.example.majika.room.*
import kotlinx.coroutines.launch

class ShoppingCartViewModel(private val repository : CartRepository) : ViewModel() {
    var listCart: LiveData<List<CartEntity>> = repository.listCart

    fun insertCart(cart: CartEntity) = viewModelScope.launch {
        repository.insertCart(cart)
    }

    fun updateCart(cart: CartEntity) = viewModelScope.launch {
        repository.updateCart(cart)
    }

    fun deleteCart(cart: CartEntity) = viewModelScope.launch {
        repository.deleteCart(cart)
    }
}