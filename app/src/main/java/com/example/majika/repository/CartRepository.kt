package com.example.majika.repository

import androidx.lifecycle.LiveData
import com.example.majika.room.*

class CartRepository (private val database: CartDatabase) {

  val listCart: LiveData<List<Cart>> = database.cartDAO.getAllCart()

  suspend fun insertCart(cart: Cart) {
    database.cartDAO.insertCart(cart)
  }

  fun getCart(item: String): Cart {
    return database.cartDAO.getCart(item)
  }

  suspend fun updateCart(cart: Cart) {
    database.cartDAO.updateCart(cart)
  }

  suspend fun deleteCart(cart: Cart) {
    database.cartDAO.deleteCart(cart)
  }
}