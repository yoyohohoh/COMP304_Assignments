package com.example.yiuyiuyoyoho_comp304sec003_lab02.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.yiuyiuyoyoho_comp304sec003_lab02.data.Status

@Composable
fun StatusDot(status: Status) {
    val color = when (status) {
        Status.NEW -> Color(0xFF89e18c)
        Status.PENDING -> Color(0xFFcb5f5f)
        Status.CLOSED -> Color.Gray
    }

    Box(
        modifier = Modifier.size(12.dp),
        //contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color, shape = CircleShape)
        )
    }
}