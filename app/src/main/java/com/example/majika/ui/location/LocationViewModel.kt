package com.example.majika.ui.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.majika.models.BranchRes
import com.example.majika.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class LocationViewModel(private val repository : Repository) : ViewModel() {
    val branchRes: MutableLiveData<Response<BranchRes>> = MutableLiveData()

    fun getBranches() = viewModelScope.launch {
        try {
            val response = repository.getBranches()
            branchRes.value = response
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}