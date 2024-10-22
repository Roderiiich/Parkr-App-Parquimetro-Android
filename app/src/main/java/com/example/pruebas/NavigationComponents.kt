package com.example.pruebas

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.pruebas.navigation.AppScreens

@Preview
@Composable
fun NavigationBottomBarPreview(){
    val navController = rememberNavController()
    NavigationBottomBar(navController)
}

@Composable
fun NavigationBottomBar(navController : NavController) {

        NavigationBar {
            NavigationBarItem(
                label = {
                    Text(text = "Ingreso")
                },
                selected = false,
                onClick = {
                    navController.navigate(AppScreens.IngresoScreen.route)
                },
                icon = {
                    Icon(Icons.Filled.Home, "Home")
                }
            )
            NavigationBarItem(
                label = {
                    Text(text = "Listado")
                },
                selected = false,
                onClick = {
                    navController.navigate(AppScreens.ListadoScreen.route)
                },
                icon = {
                    Icon(Icons.Filled.List, "listado")
                }
            )
            NavigationBarItem(
                label = {
                    Text(text = "Configuracion")
                },
                selected = false,
                onClick = {
                    navController.navigate(AppScreens.ConfiguracionScreen.route)
                },
                icon = {
                    Icon(Icons.Filled.Build, "Config")
                }
            )
        }
    }
