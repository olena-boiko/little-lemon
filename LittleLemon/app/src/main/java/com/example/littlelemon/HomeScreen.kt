package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.component.Header
import com.example.littlelemon.ui.component.HeroBanner
import com.example.littlelemon.ui.component.MenuItem

@Composable
fun HomeScreen(navController: NavController) {
    val menuItems =
        AppDatabase.getDatabase(context = LocalContext.current).menuDao().getAll().observeAsState()

    val itemsByCategory = menuItems.value?.groupBy { it.category }

    var selectedCategory by remember { mutableStateOf<String?>(null) }
    if (selectedCategory == null) {
        selectedCategory = itemsByCategory?.keys?.first()
    }

    var searchQuery by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Header()
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier
                    .height(48.dp)
                    .width(48.dp)
                    .clickable { navController.navigate(Profile.route) })
        }

        HeroBanner(
            onSearchQueryChanged = { searchQuery = it }
        )

        Text(
            text = "ORDER FOR DELIVERY!",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Categories(
            itemsByCategory?.keys?.toList(),
            selectedCategory = selectedCategory,
            onClick = { selectedCategory = it },
        )

        HorizontalDivider(thickness = 2.dp)

        MenuItems(filterMenuItems(itemsByCategory, selectedCategory, searchQuery))
    }
}

private fun filterMenuItems(
    itemsByCategory: Map<String, List<MenuItemDb>>?,
    selectedCategory: String?,
    searchQuery: String
): List<MenuItemDb> {
    return if (searchQuery.isNotBlank()) {
        itemsByCategory?.get(selectedCategory)?.filter {
            it.title.contains(searchQuery, ignoreCase = true)
        } ?: emptyList()
    } else
        itemsByCategory?.get(selectedCategory) ?: emptyList()
}

@Composable
private fun Categories(
    categories: List<String>?,
    selectedCategory: String?,
    onClick: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        categories?.map {
            FilterChip(
                selected = it == selectedCategory,
                onClick = {
                    onClick(it)
                },
                label = { Text(text = it) },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = Color.White,
                    selectedContainerColor = Color.LightGray,
                ),
            )
        }
    }
}

@Composable
private fun MenuItems(menuItems: List<MenuItemDb>?) {
    Column {
        menuItems?.map {
            MenuItem(menuItem = it)
            HorizontalDivider()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}
