package com.example.majika.repository

import androidx.lifecycle.LiveData
import com.example.majika.room.*

class CartRepository (private val database: CartDatabase) {
  val listCart: LiveData<List<CartEntity>> = database.cartDAO.getAllCart()

  suspend fun insertCart(cart: CartEntity) {
    database.cartDAO.insertCart(cart)
  }

  suspend fun updateCart(cart: CartEntity) {
    database.cartDAO.updateCart(cart)
  }

  suspend fun deleteCart(cart: CartEntity) {
    database.cartDAO.deleteCart(cart)
  }
}