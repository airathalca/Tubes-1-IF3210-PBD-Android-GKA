package com.example.majika.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://fae7-114-122-101-70.ap.ngrok.io")
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