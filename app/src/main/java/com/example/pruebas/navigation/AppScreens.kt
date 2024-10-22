package com.example.pruebas.navigation

sealed class AppScreens(val route: String) {

    object ListadoScreen : AppScreens("ListadoScreen")
    object IngresoScreen : AppScreens("IngresoScreen")
    object ConfiguracionScreen : AppScreens("ConfiguracionScreen")
    object DetalleScreen : AppScreens("DetalleScreen/{patente}")
}