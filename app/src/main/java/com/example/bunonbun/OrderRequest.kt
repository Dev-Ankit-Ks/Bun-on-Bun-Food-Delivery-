package com.example.bunonbun

import com.example.bunonbun.Models.CartItems

data class OrderRequest(val cartItem: List<CartItems>,  val name: String, val address: String)
