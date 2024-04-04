package com.example.littlelemon.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun HeroBanner(
    onSearchQueryChanged: (String) -> Unit
) {
    val searchPhrase = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.background(Green),
    ) {
        Row(
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
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
        TextField(
            value = searchPhrase.value,
            onValueChange = {
                searchPhrase.value = it
                onSearchQueryChanged(it)
            },
            placeholder = { Text("Enter search phrase") },
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
            shape = RoundedCornerShape(16.dp),
            singleLine = true,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HeroBannerPreview() {
    HeroBanner(onSearchQueryChanged = {})
}
