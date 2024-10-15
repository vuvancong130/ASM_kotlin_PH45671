package com.hrap.app.asm_ph45671


//data class ProductRequest(
//    val name: String,
//    val thumbnail: String,
//    val price: Double,
//    val description: String,
//    val category: TypeProductData
//)
//
//data class ProductResponse(
//    val msg: String,
//    val product: ProductData
//)

data class ProductData(
    val id: Int,
    val name: String,
    val thumbnail: String,
    val price: Double,
    val description: String,
    val category: TypeProductData
)