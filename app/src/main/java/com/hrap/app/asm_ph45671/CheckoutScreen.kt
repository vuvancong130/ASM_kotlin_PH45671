package com.hrap.app.asm_ph45671

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun CheckoutScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Thanh Toán",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Thông tin địa chỉ nhận hàng
        Text(text = "Địa chỉ nhận hàng", fontWeight = FontWeight.Bold)
        Text(text = "cong | 2222222")
        Text(text = "143/14 đường me tri ha, phường Me Tri, quận Nam Tu Niem, Thành phố Ha Noi")
        Spacer(modifier = Modifier.height(16.dp))

        // Thông tin lựa chọn phương thức thanh toán
        PaymentMethodSelector()
    }
}

@Composable
fun PaymentOption(title: String, backgroundColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
        PaymentMethodSelector()
    }
}
@Composable
fun PaymentMethodSelector() {
    // State để theo dõi phương thức thanh toán đã chọn
    val selectedPaymentMethod = remember { mutableStateOf("paypal") } // Giá trị mặc định

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Vui lòng chọn một trong những phương thức sau:")

        // RadioButton cho PayPal
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedPaymentMethod.value == "paypal",
                onClick = { selectedPaymentMethod.value = "paypal" }
            )
            Text(text = "PayPal")
        }

        // RadioButton cho Visa
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedPaymentMethod.value == "visa",
                onClick = { selectedPaymentMethod.value = "visa" }
            )
            Text(text = "Visa")
        }

        // RadioButton cho Momo
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedPaymentMethod.value == "momo",
                onClick = { selectedPaymentMethod.value = "momo" }
            )
            Text(text = "Momo")
        }

        // RadioButton cho Zalo Pay
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedPaymentMethod.value == "zalopay",
                onClick = { selectedPaymentMethod.value = "zalopay" }
            )
            Text(text = "Zalo Pay")
        }

        // RadioButton cho thanh toán trực tiếp
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = selectedPaymentMethod.value == "cash",
                onClick = { selectedPaymentMethod.value = "cash" }
            )
            Text(text = "Thanh toán trực tiếp")
        }

        // Nút Tiếp theo
        Button(
            onClick = {
                // Xử lý khi bấm nút Tiếp theo, ví dụ: điều hướng hoặc xử lý thanh toán
                println("Selected payment method: ${selectedPaymentMethod.value}")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Tiếp theo")
        }
    }
}

