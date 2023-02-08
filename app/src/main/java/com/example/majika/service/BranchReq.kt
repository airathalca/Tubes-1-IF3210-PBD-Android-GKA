package com.example.majika.service

import com.example.majika.models.BranchRes
import retrofit2.Response
import retrofit2.http.*

interface BranchReq {
    @GET("v1/branch")
    suspend fun getBranch(): Response<BranchRes>
}