package com.example.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.littlelemon.ui.theme.Green

@ExperimentalMaterial3Api
@Composable
fun OnboardingScreen(navController: NavHostController) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)

    Surface {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Header(modifier = Modifier.align(Alignment.CenterHorizontally))
            Intro()
            Text(
                "Personal information",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(24.dp)
            )
            InputField(label = "First Name",
                value = firstName.value,
                onValueChange = { firstName.value = it })
            InputField(
                label = "Last Name",
                value = lastName.value,
                onValueChange = { lastName.value = it })
            InputField(label = "E-mail", value = email.value, onValueChange = { email.value = it })
            AppButton(label = "Register",
                onClick = {
                    if (isFormValid(
                            firstName = firstName.value,
                            lastName = lastName.value,
                            email = email.value
                        )
                    ) {
                        Toast.makeText(context, "Registration successful!", Toast.LENGTH_LONG)
                            .show()
                        sharedPreferences.edit().apply {
                            putBoolean("is_logged_in", true).apply()
                            putString("first_name", firstName.value)
                            putString("last_name", lastName.value)
                            putString("email", email.value)
                            apply()
                        }
                        navController.navigate(Home.route)
                    } else {
                        Toast.makeText(
                            context,
                            "Registration unsuccessful. Please enter all data.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            )
        }
    }
}

@Composable
private fun Intro() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Green)
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Text("Let's get to know you", style = TextStyle(color = Color.White, fontSize = 20.sp))
    }
}

private fun isFormValid(firstName: String, lastName: String, email: String): Boolean {
    return firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()
}

@ExperimentalMaterial3Api
@Preview(showSystemUi = true)
@Composable
fun OnboardingScreenPreview() {
    OnboardingScreen(rememberNavController())
}
