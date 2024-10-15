package com.hrap.app.asm_ph45671

//data class CartRequest(
//    val productId: Int,
//    val quantity: Int
//)
//
//data class CartResponse(
//    val msg: String,
//    val cart: List<CartData>? = null // Danh sách sản phẩm trong giỏ hàng (tuỳ chọn)
//)
//
//
//data class CartData(
//    val product: ProductData, // Dữ liệu sản phẩm
//    val quantity: Int,
//    val id: Int // ID của sản phẩm trong giỏ hàng
//)

data class CartData(
    val product: ProductData,
    var quantity: Int
)


