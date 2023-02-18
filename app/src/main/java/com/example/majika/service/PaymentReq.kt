package com.example.majika.service

import com.example.majika.models.PaymentRes
import retrofit2.Response
import retrofit2.http.*

interface PaymentReq {
    @POST("v1/payment/{:code}")
    suspend fun postPayment(@Path("code") code: String): Response<PaymentRes>
}