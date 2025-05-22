package com.example.bunonbun.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "main_menu_items")
data class MainMenuItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val category: String,
    val productName: String,
    val productImage: Int,
    val productPrice: Int,
    val quantity: Int
)
