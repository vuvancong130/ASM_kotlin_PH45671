package com.hrap.app.asm_ph45671

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hrap.app.asm_ph45671.ui.theme.ASM_PH45671Theme
import com.hrap.app.asm_ph45671.Model.AuthViewModel

class RegisterScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ASM_PH45671Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

val String.toColor: Color
    get() = Color(android.graphics.Color.parseColor(this))


@Composable
fun SignUp(modifier: Modifier = Modifier, authViewModel: AuthViewModel = viewModel()) {

    val fullName = remember { mutableStateOf("") }
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    // Lắng nghe thông báo lỗi từ ViewModel
    val errorMessage = authViewModel.errorMessage.observeAsState()
    val registerSuccess = authViewModel.registerResponse.observeAsState(false)

    // Lắng nghe sự kiện đăng ký thành công
    if (registerSuccess.value) {
        val context = LocalContext.current
        LaunchedEffect(Unit) {
            context.startActivity(Intent(context, LoginScreen::class.java))
        }
    }

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(color = "#F6F6F6".toColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign Up",
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp, bottom = 8.dp),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
        )

        TextField2("User name",
            "Enter your user name",
            value = username.value,
            onValueChange = {username.value = it})

        TextField2("Full name",
            "Enter your full name",
            value = fullName.value,
            onValueChange = {fullName.value = it})


        TextField2(
            title = "Password",
            placeholder = "Enter your password",
            trailingIcon = {
                IconButton(onClick = { /* Xử lý khi nhấn */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.eye_24),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            value = password.value,
            onValueChange = {password.value = it}
        )

        // Hiển thị lỗi nếu có
        errorMessage.value?.let {
            Toast.makeText(LocalContext.current, it, Toast.LENGTH_SHORT).show()
        }

        val currentContext = LocalContext.current;

        Button(
            onClick = {
                authViewModel.register(fullName.value, username.value, password.value)

            },
            modifier = Modifier
                .padding(16.dp)
                .height(45.dp)
                .width(140.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = "#FE724C".toColor,
                contentColor = "#FFFFFF".toColor
            )
        ) {
            Text(text = "SIGN UP")
        }


        val context = LocalContext.current

        Row(
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account?",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
            TextButton(
                onClick = {
                    val intent = Intent(context, LoginScreen::class.java)
                    context.startActivity(intent)
                }
            ) {
                Text(
                    text = "Login",
                    color = "#FE724C".toColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    SignUp()
}
