package com.example.majika.repository

import retrofit2.Response
import com.example.majika.models.BranchRes
import com.example.majika.service.RetrofitInstance

class Repository {

    suspend fun getBranches(): Response<BranchRes> {
        return RetrofitInstance.branchInstance.getBranch()
    }
}