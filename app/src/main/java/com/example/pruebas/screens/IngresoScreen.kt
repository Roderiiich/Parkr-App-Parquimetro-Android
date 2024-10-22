package com.example.pruebas.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Yellow

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pruebas.ParkingPreferences
import com.example.pruebas.R
import com.example.pruebas.navigation.AppScreens
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun IngresoScreen(navController: NavController, parkingPreferences: ParkingPreferences, paddingValues: PaddingValues) {
    var patente by remember { mutableStateOf(TextFieldValue("")) }

    // Obtener la fecha y hora actuales en el formato deseado
    val currentDateTime =
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))


    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupar todo el espacio disponible
            .padding(paddingValues), // Aplicar el padding
        horizontalAlignment = Alignment.CenterHorizontally, // Centrar horizontalmente
        verticalArrangement = Arrangement.Center // Centrar verticalmente
    ) {

        Text(
            modifier = Modifier

                .padding(bottom = 30.dp),

            text = "¡Bienvenido a Parkr!",
            fontSize = 37.sp, // Tamaño de fuente
            fontWeight = FontWeight.Bold, // Texto en negrita
            color = Blue, // Color del texto
            style = androidx.compose.ui.text.TextStyle(letterSpacing = 2.sp) // Espaciado entre letras
        )

        Image(

            painter = painterResource(id = R.drawable.parkr), // Reemplaza 'logo' con el nombre de tu imagen en drawable
            contentDescription = "Logo Parkr",
            modifier = Modifier
                .size(300.dp) // Tamaño de la imagen
                .padding(6.dp) // Espacio entre la imagen y el texto
                .clip(CircleShape)
                .border(2.dp, Color.LightGray, CircleShape)
        )

        TextField(
            modifier = Modifier
                .padding(top = 30.dp),
            value = patente,
            onValueChange = { patente = it },
            label = { Text("Agregar Patente") }
        )
        Button(
            modifier = Modifier
                .padding(top = 50.dp),


            onClick = {
                if (patente.text.isNotBlank()) {
                    // Guardar la patente y la fecha/hora de ingreso en SharedPreferences
                    parkingPreferences.saveVehicle(patente.text, currentDateTime)
                    Toast.makeText(navController.context, "Vehículo ingresado", Toast.LENGTH_SHORT)
                        .show()
                    navController.navigate(AppScreens.ListadoScreen.route)
                }
            }) {
            Text(
                modifier = Modifier,
                text = "Guardar Ingreso",
                fontWeight = FontWeight.Bold
            )

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize() // Ocupar todo el espacio disponible
            .padding(paddingValues), // Aplicar el padding
        horizontalAlignment = Alignment.CenterHorizontally, // Centrar horizontalmente
        verticalArrangement = Arrangement.Bottom // Centrar verticalmente
    ) {

        Text(
            modifier = Modifier

                .padding(bottom = 20.dp),

            text = "By Roderich",
            fontSize = 20.sp, // Tamaño de fuente
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,// Texto en negrita
            color = Color.LightGray, // Color del texto
            style = androidx.compose.ui.text.TextStyle(letterSpacing = 2.sp) // Espaciado entre letras
        )
    }
}


