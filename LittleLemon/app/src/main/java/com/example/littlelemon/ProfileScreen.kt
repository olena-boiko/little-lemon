package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.component.AppButton
import com.example.littlelemon.ui.component.Header
import com.example.littlelemon.ui.component.InputField

@Composable
fun ProfileScreen(navController: NavHostController) {
    val sharedPreferences = LocalContext.current.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    val firstName = remember { mutableStateOf(sharedPreferences.getString("first_name", "") ?: "") }
    val lastName = remember { mutableStateOf(sharedPreferences.getString("last_name", "") ?: "") }
    val email = remember { mutableStateOf(sharedPreferences.getString("email", "") ?: "") }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Header(modifier = Modifier.align(Alignment.CenterHorizontally))
        Text(
            "Personal information:",
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(24.dp)
        )
        InputField(label = "First Name",
            value = firstName.value,
            onValueChange = {
                firstName.value = it
                sharedPreferences.edit().putString("first_name", email.value).apply()
            })
        InputField(
            label = "Last Name",
            value = lastName.value,
            onValueChange = {
                lastName.value = it
                sharedPreferences.edit().putString("last_name", email.value).apply()
            })
        InputField(label = "E-mail", value = email.value, onValueChange = {
            email.value = it
            sharedPreferences.edit().putString("email", email.value).apply()
        })
        AppButton(label = "Log out", onClick = {
            sharedPreferences.edit().clear().apply()
            navController.popBackStack(Onboarding.route, inclusive = false)
            navController.navigate(Onboarding.route)
        })
    }
}

@ExperimentalMaterial3Api
@Preview(showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(rememberNavController())
}
