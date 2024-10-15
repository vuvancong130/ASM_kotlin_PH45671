package com.hrap.app.asm_ph45671

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @FormUrlEncoded
    @POST("/login")
    fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    // Phương thức đăng ký
    @FormUrlEncoded
    @POST("/signup")
    fun registerUser(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("fullname") fullname: String
    ): Call<RegisterResponse>

    @GET("/foods")
    fun getProducts(): Call<List<ProductData>>

    @FormUrlEncoded
    @POST("/searchFood")
    fun searchFood(@Field("text") query: String): Call<List<ProductData>>

}