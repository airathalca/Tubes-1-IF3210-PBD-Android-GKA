package com.example.majika.repository

import retrofit2.Response
import com.example.majika.models.*
import com.example.majika.service.RetrofitInstance

class Repository {
    suspend fun getBranches(): Response<BranchRes> {
        return RetrofitInstance.branchInstance.getBranch()
    }

    suspend fun getMenus(): Response<MenuRes> {
        return RetrofitInstance.menuInstance.getMenu()
    }

    suspend fun getFoodMenus(): Response<MenuRes> {
        return RetrofitInstance.menuInstance.getFoodMenu()
    }

    suspend fun getDrinkMenus(): Response<MenuRes> {
        return RetrofitInstance.menuInstance.getDrinkMenu()
    }

    suspend fun doPayment(code: String): Response<PaymentRes> {
        return RetrofitInstance.paymentInstance.postPayment(code)
    }
}