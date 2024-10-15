package com.hrap.app.asm_ph45671.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hrap.app.asm_ph45671.ProductData
import com.hrap.app.asm_ph45671.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {

    // LiveData chứa danh sách sản phẩm
    private val _products = MutableLiveData<List<ProductData>>()
    val products: LiveData<List<ProductData>> get() = _products

    init {
        fetchProducts()
    }

    // Gọi API để lấy danh sách sản phẩm
    private fun fetchProducts() {
        RetrofitClient.api.getProducts().enqueue(object : Callback<List<ProductData>> {
            override fun onResponse(call: Call<List<ProductData>>, response: Response<List<ProductData>>) {
                if (response.isSuccessful) {
                    _products.value = response.body() ?: emptyList()
                } else {
                    _products.value = emptyList()
                }
            }

            override fun onFailure(call: Call<List<ProductData>>, t: Throwable) {
                _products.value = emptyList() // Xử lý lỗi khi không tải được dữ liệu
            }
        })
    }
    // Phương thức tìm kiếm món ăn
    fun searchFood(query: String) {
        RetrofitClient.api.searchFood(query).enqueue(object : Callback<List<ProductData>> {
            override fun onResponse(call: Call<List<ProductData>>, response: Response<List<ProductData>>) {
                if (response.isSuccessful) {
                    _products.value = response.body() ?: emptyList()
                } else {
                    _products.value = emptyList()
                }
            }

            override fun onFailure(call: Call<List<ProductData>>, t: Throwable) {
                _products.value = emptyList() //
            }
        })
    }
}

