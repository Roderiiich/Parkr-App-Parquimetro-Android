package com.example.pruebas.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pruebas.screens.*
import com.example.pruebas.ParkingPreferences
import com.example.pruebas.NavigationBottomBar


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppNavigation(parkingPreferences: ParkingPreferences) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val currentRoute = navController.currentBackStackEntry?.destination?.route
            NavigationBottomBar(navController)
        }
    ) { paddingValues -> // Padding para no solaparse con la bottomBar
        NavHost(
            navController = navController,
            startDestination = AppScreens.IngresoScreen.route
        ) {

            composable(AppScreens.ListadoScreen.route) {
                ListadoScreen(navController, parkingPreferences, paddingValues)
            }
            composable(AppScreens.IngresoScreen.route) {
                IngresoScreen(navController, parkingPreferences, paddingValues)
            }
            composable(AppScreens.ConfiguracionScreen.route) {
                ConfiguracionScreen(navController, parkingPreferences, paddingValues)
            }
            composable(AppScreens.DetalleScreen.route + "/{patente}") { backStackEntry ->
                val patente = backStackEntry.arguments?.getString("patente")
                DetalleScreen(navController, patente ?: "", parkingPreferences, paddingValues)
            }
        }
    }
}