package com.hrap.app.asm_ph45671

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrap.app.asm_ph45671.Model.History.Order

@Composable
fun OrderDetailScreen(order: Order) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Chi tiết đơn hàng", style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        // Thông tin cửa hàng
        Text(text = order.storeName, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(text = order.storeAddress)
        Text(text = order.storePhone)

        Spacer(modifier = Modifier.height(8.dp))

        // Thông tin hóa đơn
        Text(text = "Hóa Đơn Thanh Toán", fontWeight = FontWeight.Bold)
        Text(text = "Ngày: ${order.date}")
        Text(text = "Trạng thái đơn hàng: ${order.status}", color = Color.Green)

        Spacer(modifier = Modifier.height(16.dp))

        // Chi tiết số lượng món ăn
        Text(text = "Số lượng  món: ${order.mainDishQuantity}")
        Text(text = "Tổng tiền : ${order.mainDishTotal}VND")

        Spacer(modifier = Modifier.height(16.dp))

        // Địa chỉ giao hàng
        Text(text = "Số nhà: ${order.houseNumber}")
        Text(text = "Đường: ${order.street}")
        Text(text = "Phường: ${order.ward}")
        Text(text = "Quận: ${order.district}")
        Text(text = "Thành phố: ${order.city}")

        Spacer(modifier = Modifier.height(16.dp))

        // Tổng tiền
        Text(text = "Tổng tiền", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Text(text = "${order.totalPrice}VND", fontWeight = FontWeight.Bold, fontSize = 24.sp)
    }
}
