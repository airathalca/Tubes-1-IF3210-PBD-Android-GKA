package com.example.majika.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.114:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val branchInstance: BranchReq by lazy {
        retrofit.create(BranchReq::class.java)
    }

}