package com.example.littlelemon.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.Green
import com.example.littlelemon.ui.theme.Yellow

@Composable
fun HeroBanner() {
    Row(
        modifier = Modifier
            .background(Green)
            .padding(16.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(.6f)
                .padding(end = 16.dp)
        ) {
            Text(
                text = "Little Lemon",
                style = MaterialTheme.typography.titleLarge,
                color = Yellow,
            )
            Text(
                text = "Chicago",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            Text(
                text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
        Column {
            Image(
                painter = painterResource(id = R.drawable.hero),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(8.dp)
                    )
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HeroBannerPreview() {
    HeroBanner()
}
