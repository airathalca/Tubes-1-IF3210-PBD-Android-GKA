package com.example.majika.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import android.content.Context

@Database(entities = [TransactionEntity::class], version = 1)

abstract class TransactionDatabase : RoomDatabase() {
    
}
