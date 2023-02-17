package com.example.majika.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(cart: Cart)

    @Query("SELECT * FROM cart")
    fun getAllCart(): LiveData<List<Cart>>

    @Query("SELECT * from cart where item = :item")
    fun getCart(item: String): Cart

    @Update()
    suspend fun updateCart(cart: Cart)

    @Delete
    suspend fun deleteCart(cart: Cart)

}
