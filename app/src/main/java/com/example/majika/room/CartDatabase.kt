package com.example.majika.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import android.content.Context

@Database(entities = [CartEntity::class], version = 1)
abstract class CartDatabase : RoomDatabase() {
    abstract val cartDAO: CartDAO

    companion object {
        @Volatile
        private var INSTANCE: CartDatabase? = null
        fun getDatabase(context: Context): CartDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CartDatabase::class.java,
                        "cart_database"
                    )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
