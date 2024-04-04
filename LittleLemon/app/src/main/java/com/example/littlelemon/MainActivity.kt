package com.example.littlelemon

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {

    private val httpClient: HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                },
                contentType = ContentType("text", "plain")
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false)

        lifecycleScope.launch(Dispatchers.IO) {
            val menu = fetchContent()

            AppDatabase.getDatabase(context = this@MainActivity).menuDao()
                .insertAll(
                    menuItemsDb = menu.items.map {
                        MenuItemDb(
                            id = it.id,
                            title = it.title,
                            description = it.description,
                            price = it.price,
                            category = it.category,
                            image = it.image
                        )
                    }
                )
        }

        setContent {
            val navController = rememberNavController()

            LittleLemonTheme {
                NavigationComposable(navController = navController, isLoggedIn = isLoggedIn)
            }
        }
    }

    private suspend fun fetchContent(): MenuNetwork {
        val response =
            httpClient
                .get(menuUrl)

        return response.body()
    }

    companion object {
        const val menuUrl =
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    }
}
