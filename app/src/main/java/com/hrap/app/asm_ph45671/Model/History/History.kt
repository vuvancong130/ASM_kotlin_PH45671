package com.hrap.app.asm_ph45671.Model.History

import com.hrap.app.asm_ph45671.CartData

data class HistoryData(
    val id: String,
    val paymentMethod: String,
    val product: CartData,
    val totalAmount: Double,
    val date: String
)
