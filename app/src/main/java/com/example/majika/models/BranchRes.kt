package com.example.majika.models

data class BranchData (
    val name: String,
    val popular_food: String,
    val address: String,
    val contact_person: String,
    val phone_number: String,
    val longitude: Float,
    val latitude: Float,
)

data class BranchRes (
    val data: List<BranchData>,
    val size: Int,
)
