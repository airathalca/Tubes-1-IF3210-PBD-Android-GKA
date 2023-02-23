package com.example.majika.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://172.20.10.3:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val branchInstance: BranchReq by lazy {
        retrofit.create(BranchReq::class.java)
    }

    val menuInstance: MenuReq by lazy {
        retrofit.create(MenuReq::class.java)
    }

    val paymentInstance: PaymentReq by lazy {
        retrofit.create(PaymentReq::class.java)
    }
}