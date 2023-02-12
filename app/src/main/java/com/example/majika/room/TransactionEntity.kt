package com.example.majika.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.majika.models.Menu
import com.example.majika.utils.getRandomString

@Entity(tableName = "transaction")
data class Cart (
    @PrimaryKey(autoGenerate = false)
    var id: String = getRandomString(32),

    @ColumnInfo(name = "list_menu")
    var listMenu: ArrayList<Menu> = ArrayList(),

    @ColumnInfo(name = "status")
    var status: String = "pending",
)
