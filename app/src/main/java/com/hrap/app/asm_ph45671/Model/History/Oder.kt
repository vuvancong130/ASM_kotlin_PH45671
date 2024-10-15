package com.hrap.app.asm_ph45671.Model.History

import com.hrap.app.asm_ph45671.CartData

data class Order(
    val storeName: String,
    val storeAddress: String,
    val storePhone: String,
    val date: String,
    val status: String,
    val mainDishQuantity: Int,
    val mainDishTotal: Double,
    val totalPrice: Double,
    val dishes: List<CartData>, // Danh sách các món
    val houseNumber: String,
    val street: String,
    val ward: String,
    val district: String,
    val city: String
)
