package com.example.majika.ui.shoppingCart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoppingCartViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Shopping Cart Fragment"
    }
    val text: LiveData<String> = _text
}