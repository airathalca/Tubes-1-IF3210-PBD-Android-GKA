package com.example.majika.ui.foodBank

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodBankViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Food Bank Fragment"
    }
    val text: LiveData<String> = _text
}