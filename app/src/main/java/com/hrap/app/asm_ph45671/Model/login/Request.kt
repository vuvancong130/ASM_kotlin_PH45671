package com.hrap.app.asm_ph45671

// Đăng ký - Signup
data class SignupRequest(
    val fullname: String,
    val username: String,
    val password: String
)

data class RegisterResponse(
    val userId: String,     // ID của người dùng sau khi đăng ký thành công
    val message: String     // Thông báo kết quả đăng ký
)

// Đăng nhập - Login
data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val userId: String,     // ID của người dùng
    val username: String,   // Tên đăng nhập
    val token: String,      // Token xác thực (JWT)
    val message: String     // Thông báo kết quả đăng nhập
)

// AuthResponse - Dùng chung cho phản hồi thành công hoặc thất bại
data class AuthResponse(
    val success: Boolean,   // Trạng thái thành công hay thất bại
    val message: String     // Thông báo kết quả (có thể là thông báo lỗi hoặc thành công)
)
