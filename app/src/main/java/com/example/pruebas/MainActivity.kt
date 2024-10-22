package com.example.pruebas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.pruebas.navigation.AppNavigation
import com.example.pruebas.ui.theme.PruebasTheme





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val parkingPreferences = ParkingPreferences(this)

        setContent {
            PruebasTheme {
                AppNavigation(parkingPreferences = parkingPreferences)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PruebasTheme {

    }
}