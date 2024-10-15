package com.hrap.app.asm_ph45671

import CartViewModel
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.hrap.app.asm_ph45671.ViewModel.ProductViewModel

@Composable
fun HomeScreen(navController: NavHostController, viewModel: CartViewModel) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedProduct by remember { mutableStateOf<ProductData?>(null) }
    val viewModelProduct: ProductViewModel = viewModel()
    val products by viewModelProduct.products.observeAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderSection()
        Banner()
        SearchBar(searchQuery) { query ->
            searchQuery = query
            viewModelProduct.searchFood(query)
        }

        if (products.isNotEmpty()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(products) { product ->
                    ProductItem(product, context, navController) {
                        selectedProduct = product
                    }
                }
            }
        } else {
            Text(text = "Không có sản phẩm", modifier = Modifier.padding(16.dp))
        }

        selectedProduct?.let { product ->
            ShowDialogDetail(
                product = product,
                navController = navController, // Truyền navController vào
                onDismiss = { selectedProduct = null },
                onAddToCart = { quantity ->
                    viewModel.addToCart(product, quantity)
                    selectedProduct = null
//                    navController.navigate("cart") // Điều hướng đến màn hình giỏ hàng

                    Log.d("AddToCart", "Adding product: $product with quantity: $quantity")
                }

            )
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(13.dp),
    ) {
        // Hình ảnh ở đầu
        Image(
            painter = painterResource(id = R.drawable.lo_go),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Text(
            text = "Cưm Tứm Đim",
            style = MaterialTheme.typography.titleLarge,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 7.dp, top = 8.dp) // Giảm padding giữa logo và chữ
        )
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.banner), // Thay R.drawable.banner_image bằng id của ảnh banner
        contentDescription = "Banner Image",
        modifier = modifier
            .fillMaxWidth() // Đặt chiều rộng bằng chiều rộng màn hình
            .padding(vertical = 10.dp, horizontal = 10.dp), // Thêm khoảng cách 10dp bên dưới ảnh
        contentScale = ContentScale.Crop // Điều chỉnh kích thước ảnh nếu cần
    )
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Tìm món ăn...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp)
    )
}

@Composable
fun ProductItem(
    product: ProductData,
    context: Context,
    navController: NavController,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick() // Gọi hàm onClick khi nhấn vào sản phẩm
            }
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = "#DFDDDD".toColor
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            DisplayImageFromUri(product.thumbnail)
            Column(
                modifier = Modifier.padding(top = 8.dp, start = 10.dp)
            ) {
                Text(text = product.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Text(text = "${product.price}K", fontSize = 16.sp, color = "#FE724C".toColor)
            }
        }
    }
}

@Composable
fun DisplayImageFromUri(imageUri: String) {
    Image(
        painter = rememberImagePainter(
            data = imageUri,
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
            .size(60.dp), // Chỉnh sửa kích thước theo nhu cầu
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ShowDialogDetail(
    product: ProductData,
    navController: NavController, // Thêm tham số navController để điều hướng
    onDismiss: () -> Unit,
    onAddToCart: (Int) -> Unit
) {
    var quantity by remember { mutableStateOf(1) }

    AlertDialog(
        onDismissRequest = onDismiss,

        text = {
            Row {
                Image(
                    painter = rememberImagePainter(product.thumbnail),
                    contentDescription = null,
                    modifier = Modifier
                        .size(140.dp, 120.dp)
                        .padding(horizontal = 5.dp)
                        .clip(RoundedCornerShape(50.dp)),
                    contentScale = ContentScale.FillHeight
                )
                Column {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        fontSize = 18.sp,
                    )
                    Text(text = product.description, style = MaterialTheme.typography.bodyMedium)

                    Text(
                        text = "Giá tiền: ${product.price} VND",
                        style = MaterialTheme.typography.titleMedium, fontSize = 13.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    QuantitySelector(
                        quantity = quantity,
                        onQuantityChange = { newQuantity -> quantity = newQuantity })
                }
            }
        },
        confirmButton = {
            Button(onClick = {
                onAddToCart(quantity)
                Log.e("sss", quantity.toString())
//                navController.navigate("cart") // Điều hướng đến màn hình giỏ hàng sau khi thêm sản phẩm
                onDismiss()
            }) {
                Text("Thêm vào giỏ hàng")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Đóng")
            }
        }
    )
}

@Composable
fun QuantitySelector(quantity: Int, onQuantityChange: (Int) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { if (quantity > 0) onQuantityChange(quantity - 1) }) {
            Icon(Icons.Filled.Remove, contentDescription = "Giảm") // Dấu trừ
        }
        Text(text = "$quantity", style = MaterialTheme.typography.bodyLarge)
        IconButton(onClick = { onQuantityChange(quantity + 1) }) {
            Icon(Icons.Filled.Add, contentDescription = "Tăng") // Dấu cộng
        }
    }
}
