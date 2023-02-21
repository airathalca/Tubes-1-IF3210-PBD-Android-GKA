package com.example.majika.ui.payment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.majika.models.PaymentRes
import com.example.majika.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class PaymentViewModel(private val repository: Repository) : ViewModel() {
    val paymentRes: MutableLiveData<Response<PaymentRes>> = MutableLiveData()

    fun doPayment(code: String) = viewModelScope.launch {
        val response = repository.doPayment(code)
        paymentRes.value = response
    }
}