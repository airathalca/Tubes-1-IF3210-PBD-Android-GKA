package com.example.majika.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionDAO {
    @Insert
    suspend fun insertTransaction(transactionEntity: TransactionEntity)

    @Query("SELECT * FROM transaction WHERE status = :status LIMIT 1")
    fun getTransactionByStatus(status: String): LiveData<List<TransactionEntity>>

    @Query("UPDATE transaction SET status = :status WHERE id = :id")
    suspend fun updateTransactionStatus(id: String, status: String)

    @Query("UPDATE transaction SET list_menu = :listMenu WHERE id = :id")
    suspend fun updateTransactionListMenu(id: String, listMenu: ArrayList<Menu>)

}
