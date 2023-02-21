package com.example.majika.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: CartEntity)

    @Query("SELECT * FROM cart")
    fun getAllCart(): LiveData<List<CartEntity>>

    @Query("SELECT * FROM cart WHERE item = :item")
    fun getCart(item: String): CartEntity

    @Update()
    suspend fun updateCart(cart: CartEntity)

    @Delete()
    suspend fun deleteCart(cart: CartEntity)

    @Query("DELETE FROM cart")
    suspend fun deleteAllCart()
}
