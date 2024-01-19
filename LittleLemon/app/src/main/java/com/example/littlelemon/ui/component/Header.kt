package com.example.littlelemon.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R

@Composable
fun Header(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = null,
        contentScale = ContentScale.FillHeight,
        modifier = modifier
            .height(80.dp)
            .padding(20.dp)
    )
}
