package com.example.majika.service

import com.example.majika.models.MenuRes
import retrofit2.Response
import retrofit2.http.*

interface MenuReq {
    @GET("v1/menu")
    suspend fun getMenu(): Response<MenuRes>

    @GET("v1/menu/food")
    suspend fun getFoodMenu(): Response<MenuRes>

    @GET("v1/menu/drink")
    suspend fun getDrinkMenu(): Response<MenuRes>
}