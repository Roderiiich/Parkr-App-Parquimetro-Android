package com.example.pruebas.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pruebas.ParkingPreferences
import com.example.pruebas.R
import com.example.pruebas.navigation.AppScreens
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


@Composable
fun DetalleScreen(
    navController: NavController,
    patente: String,
    parkingPreferences: ParkingPreferences,
    paddingValues: PaddingValues
) {
    // Obtener la fecha de ingreso y el valor por minuto
    val vehicleDetails = parkingPreferences.getVehicleDetails(patente)
    val fechaIngreso = vehicleDetails.first // La fecha y hora de ingreso
    val valorPorMinuto = parkingPreferences.getValorPorMinuto()

    // Convertir la fecha de ingreso a LocalDateTime
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
    val ingresoDateTime = LocalDateTime.parse(fechaIngreso, formatter)

    // Calcular minutos transcurridos
    val currentDateTime = LocalDateTime.now()
    val minutosTranscurridos = ChronoUnit.MINUTES.between(ingresoDateTime, currentDateTime)

    // Calcular el valor a pagar
    val totalPagar = minutosTranscurridos * valorPorMinuto

    Column(modifier = Modifier.padding(paddingValues)) {
        Text(
            modifier = Modifier.padding(20.dp),
            text = "Detalle del Vehículo",
            fontSize = 37.sp,
            fontWeight = FontWeight.Bold,
            color = Blue,
            style = androidx.compose.ui.text.TextStyle(letterSpacing = 2.sp),
            textAlign = TextAlign.Center
        )

        Column(modifier = Modifier.padding(top = 20.dp, start = 50.dp)) {
            Image(
                painter = painterResource(id = R.drawable.rayo),
                contentDescription = "Logo Parkr",
                modifier = Modifier
                    .size(300.dp) // Tamaño de la imagen
                    .padding(top = 5.dp)
            )
            Text(text = "Patente: $patente", fontSize = 20.sp)
            Text(modifier = Modifier.padding(top = 5.dp), text = "Hora de ingreso: $fechaIngreso", fontSize = 20.sp)
            Text(modifier = Modifier.padding(top = 5.dp), text = "Minutos de estadía: $minutosTranscurridos minutos", fontSize = 20.sp)
            Text(modifier = Modifier.padding(top = 5.dp), text = "Valor por minuto: $$valorPorMinuto", fontSize = 20.sp)
            Text(modifier = Modifier.padding(top = 5.dp), text = "VALOR A PAGAR: $$totalPagar", fontSize = 20.sp)
        }

        Box(
            modifier = Modifier
                .fillMaxSize() // Ocupa todo el tamaño disponible en la pantalla
                .padding(10.dp), // Añade padding opcional en la parte inferior
            contentAlignment = Alignment.BottomCenter // Alinea el contenido en la parte inferior, centrado horizontalmente
        ) {
            Button(
                onClick = {
                    parkingPreferences.removeVehicle(patente)

                    Toast.makeText(navController.context, "Salió Correctamente", Toast.LENGTH_SHORT)
                        .show()
                    navController.navigate(AppScreens.ListadoScreen.route)
                }
            ) {
                Text("Marcar salida")
            }
        }
    }
}
