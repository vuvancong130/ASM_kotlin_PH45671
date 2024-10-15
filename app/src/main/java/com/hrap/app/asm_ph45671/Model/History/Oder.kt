package com.hrap.app.asm_ph45671.Model.History
data class Order(
    val id: String,
    val storeName: String,
    val storeAddress: String,
    val storePhone: String,
    val date: String,
    val status: String,
    val mainDishQuantity: Int,
    val mainDishTotal: Int,
    val totalQuantity: Int,
    val houseNumber: String,
    val street: String,
    val ward: String,
    val district: String,
    val city: String,
    val totalPrice: Int
)
