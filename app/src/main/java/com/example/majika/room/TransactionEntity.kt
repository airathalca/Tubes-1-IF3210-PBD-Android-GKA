package com.example.majika.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.majika.models.Menu

@Entity(tableName = "transaction")
data class Cart (
    @PrimaryKey(autoGenerate = false)
    var id: String = "",

    @ColumnInfo(name = "list_menu")
    var listMenu: ArrayList<Menu> = ArrayList(),

    @ColumnInfo(name = "total_price")
    var totalPrice: Int = 0
)
