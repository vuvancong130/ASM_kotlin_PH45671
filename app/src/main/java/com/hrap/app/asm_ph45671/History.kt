package com.hrap.app.asm_ph45671

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class History : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HistoryScreen()
        }
    }
}

@Composable
fun HistoryScreen() {
    Surface {
        // Nội dung của màn hình chính
//        Text("Đây là Trang Chủ", style = MaterialTheme.typography.h4)
        Text(text = "Lịch sử ")
    }
}

@Preview
@Composable
fun PreviewHistoryScreen() {
    HistoryScreen()
}
