package com.example.pruebas.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pruebas.ParkingPreferences
import com.example.pruebas.navigation.AppScreens

@Composable
fun ListadoScreen(navController: NavController, parkingPreferences: ParkingPreferences, paddingValues: PaddingValues) {
    // Obtener la lista de vehículos guardados en SharedPreferences
    val vehicles = parkingPreferences.getAllVehicles()

    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(paddingValues)) {
        // Barra de búsqueda
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Buscar Patente") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        // Mostrar la cantidad de plazas disponibles
        val plazasDisponibles = parkingPreferences.getPlazasDisponibles() - vehicles.size
        Text(
            text = "Cupos Disponibles: $plazasDisponibles",
            fontSize=20.sp,
            fontWeight = FontWeight.Bold, // Texto en negrita
            color = Blue, // Color del texto
            style = androidx.compose.ui.text.TextStyle(letterSpacing = 2.sp),
            modifier = Modifier.padding(16.dp))

        // Filtrar la lista de vehículos en base a la búsqueda
        val filteredVehicles = vehicles.filter { it.first.contains(searchQuery.text, ignoreCase = true) }

        // Lista de vehículos
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(filteredVehicles) { vehicle ->
                VehicleItem(navController = navController, patente = vehicle.first, fechaHora = vehicle.second)
            }
        }
    }
}

@Composable
fun VehicleItem(navController: NavController, patente: String, fechaHora: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // Navegar a la pantalla de detalles cuando se hace clic en un vehículo
                navController.navigate("${AppScreens.DetalleScreen.route}/$patente")
            }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                modifier = Modifier

                    .padding(bottom = 20.dp),

                text = "PATENTE",
                fontSize = 20.sp, // Tamaño de fuente
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,// Texto en negrita
                color = Blue, // Color del texto
                style = androidx.compose.ui.text.TextStyle(letterSpacing = 2.sp) // Espaciado entre letras
            )

            Text(
                text = "$patente",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,// Texto en negrita
                color = Color.Black)
        }
        Column {
            Text(
                modifier = Modifier

                    .padding(bottom = 20.dp),

                text = "INGRESO",
                fontSize = 20.sp, // Tamaño de fuente
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,// Texto en negrita
                color = Blue, // Color del texto
                style = androidx.compose.ui.text.TextStyle(letterSpacing = 2.sp) // Espaciado entre letras
            )
            Text(
                text = "$fechaHora",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,// Texto en negrita
                color = Color.Black)
        }
    }
}