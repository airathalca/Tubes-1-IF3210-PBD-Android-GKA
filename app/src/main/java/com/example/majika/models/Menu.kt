package com.example.majika.models

data class Menu (
    val name: String,
    val price: Int,
    val sold: Int,
    val description: String,
    val type: String,
    val currency: String,
)