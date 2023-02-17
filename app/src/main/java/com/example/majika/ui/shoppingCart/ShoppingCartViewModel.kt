package com.example.majika.ui.shoppingCart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.majika.repository.CartRepository
import com.example.majika.room.*
import kotlinx.coroutines.launch

class ShoppingCartViewModel(private val repository : CartRepository) : ViewModel() {
    
    var listCart: LiveData<List<Cart>> = repository.listCart
    var cart: Cart = Cart()

    fun insertCart(cart: Cart) = viewModelScope.launch {
        repository.insertCart(cart)
    }

    fun getCart(item: String) = viewModelScope.launch {
        cart = repository.getCart(item)
    }

    fun updateCart(cart: Cart) = viewModelScope.launch {
        repository.updateCart(cart)
    }

    fun deleteCart(cart: Cart) = viewModelScope.launch {
        repository.deleteCart(cart)
    }
}