package com.example.majika.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.majika.models.Menu

@Entity(tableName = "cart")
data class CartEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "item")
    var item: String = "",

    @ColumnInfo(name = "price")
    var price: Int = 0,

    @ColumnInfo(name = "quantity")
    var quantity: Int = 0,
)
