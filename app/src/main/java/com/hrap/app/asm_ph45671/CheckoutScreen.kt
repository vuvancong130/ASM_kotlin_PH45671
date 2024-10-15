package com.hrap.app.asm_ph45671

import CartViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hrap.app.asm_ph45671.ViewModel.HistoryViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CheckoutScreen(
    navController: NavHostController, historyViewModel: HistoryViewModel, cartViewModel: CartViewModel,
) {
    val context = LocalContext.current
    val cartItems by cartViewModel.cartItems.observeAsState(initial = emptyList())
    val totalItems = getTotalItems(cartItems)
    val totalPrice = getTotalPrice(cartItems)

    // Tạo ngày hiện tại
    val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())


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
        PaymentMethodSelector(historyViewModel, navController, totalPrice, totalItems, cartItems, currentDate)

    }
}

@Composable
fun PaymentMethodSelector(historyViewModel: HistoryViewModel,
                          navController: NavHostController,
                          totalPrice: Double,
                          totalItems: Int,
                          cartItems: List<CartData>,
                          currentDate: String) {
    val selectedPaymentMethod = remember { mutableStateOf("paypal") } // Giá trị mặc định
    val totalAmount = 140.0 // Giá trị tổng tiền bạn có thể thay đổi theo logic của mình
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

        Button(
            onClick = {
                // Thêm lịch sử đơn hàng vào ViewModel
                cartItems.forEach { cartItem ->
                    historyViewModel.addHistory(
                        id = System.currentTimeMillis().toString(), // Tạo ID duy nhất
                        paymentMethod = selectedPaymentMethod.value,
                        products = cartItems, // Truyền danh sách sản phẩm vào
                        totalAmount = totalPrice,
                        date = currentDate
                    )
                }

                // Điều hướng ra khỏi màn hình sau khi hoàn thành
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Tiếp theo")
        }
    }
}

