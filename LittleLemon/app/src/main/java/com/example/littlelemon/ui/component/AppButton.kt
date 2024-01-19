package com.example.littlelemon.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.ui.theme.Yellow

@Composable
fun AppButton(
    label: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Yellow),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Red)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(label, style = TextStyle(fontSize = 16.sp, color = Color.Black))
    }
}
