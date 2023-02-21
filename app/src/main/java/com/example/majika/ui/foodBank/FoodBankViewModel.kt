package com.example.majika.ui.foodBank

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.majika.models.MenuRes
import com.example.majika.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class FoodBankViewModel(private val repository : Repository) : ViewModel() {
    val menuRes: MutableLiveData<Response<MenuRes>> = MutableLiveData()

    fun getMenus() = viewModelScope.launch {
        val response = repository.getMenus()
        menuRes.value = response
    }
}