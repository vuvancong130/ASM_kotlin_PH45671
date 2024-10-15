package com.hrap.app.asm_ph45671

import CartViewModel
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter

@JvmOverloads
@Composable

fun CartScreen(viewModel: CartViewModel, navController: NavHostController) {
    val cartViewModel: CartViewModel = viewModel
    val cartItems by cartViewModel.cartItems.observeAsState(initial = emptyList())
    val totalItems = getTotalItems(cartItems)
    val totalPrice = getTotalPrice(cartItems)

    Log.e("CartScreen", "Current cart items: $cartItems") // Log để kiểm tra dữ liệu

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Giỏ hàng",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )
        if (cartItems.isNotEmpty()) {
            LazyColumn(modifier = Modifier.height(560.dp)) {
                items(cartItems) { cartItem ->
                    CartItemRow(cartItem) { newQuantity ->
                        viewModel.updateQuantity(
                            cartItem.product.id.toString(),
                            newQuantity
                        )
                        Log.e("sss", cartItem.product.name)
                    }
                }
            }
            Divider(
                color = Color.Gray, // Màu của dấu kẻ
                thickness = 1.dp, // Độ dày của dấu kẻ
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Spacer(modifier = Modifier.weight(1f)) // Đẩy Row xuống cuối
            Column(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 30.dp)
                    .align(Alignment.End),
                verticalArrangement = Arrangement.Bottom
            ) {
                Row(
                    modifier = Modifier
                ) {
                    Text(
                        text = "Số lượng món ăn: ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$totalItems", fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                }
                Text(
                    text = "Tổng tiền: $totalPrice VND",
                    modifier = Modifier
                        .width(200.dp)
                        .padding(top = 10.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Divider(
                    color = Color.Gray, // Màu của dấu kẻ
                    thickness = 1.dp, // Độ dày của dấu kẻ
                    modifier = Modifier.padding(vertical = 8.dp)
                )


                Button(
                    {
                        viewModel.clearCart() // Xóa hết giỏ hàng
                        navController.navigate("home")
                    }, Modifier.fillMaxWidth(),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFA500), // Màu cam
                    )
                ) {
                    Text(
                        text = "Huỷ",
                        modifier = Modifier,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    onClick = { navController.navigate("checkout") }, Modifier.fillMaxWidth(),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFA500), // Màu cam
                    )
                ) {
                    Text(
                        text = "Thanh Toán",
                        modifier = Modifier,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else {
            Text(text = "Giỏ hàng trống", modifier = Modifier.padding(16.dp))
        }
    }

}

@Composable
fun CartItemRow(cartItem: CartData, onQuantityChange: (Int) -> Unit) {
    val totalPrice = cartItem.product.price * cartItem.quantity
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(
                data = cartItem.product.thumbnail,
                builder = {
                    error(R.drawable.error_placeholder) // Hiển thị ảnh lỗi nếu không tải được
                    listener(
                        onError = { request, throwable ->
                            Log.e(
                                "CoilError",
                                "Error loading image: ${throwable.throwable ?: "Unknown error"}"
                            )
                        },
                        onSuccess = { _, _ ->
                            Log.d("CoilSuccess", "Image loaded successfully")
                        }
                    )
                }
            ),
            contentDescription = "Selected Image",
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .size(50.dp), // Chỉnh sửa kích thước theo nhu cầu
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 20.dp)
        ) {
            Text(
                text = cartItem.product.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .width(220.dp),
                fontSize = 20.sp,
            )
            Text(
                text = "${totalPrice} VND",
                modifier = Modifier,
                color = Color.Red,
                fontSize = 20.sp
            )

        }
        QuantitySelector(quantity = cartItem.quantity, onQuantityChange = onQuantityChange)

    }
}

fun getTotalItems(cartItems: List<CartData>): Int {
    return cartItems.sumOf { it.quantity }
}

fun getTotalPrice(cartItems: List<CartData>): Double {
    return cartItems.sumOf { it.product.price * it.quantity }
}


