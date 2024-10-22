package com.example.pruebas.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pruebas.ParkingPreferences
import com.example.pruebas.R
import com.example.pruebas.navigation.AppScreens


@Composable
fun ConfiguracionScreen(navController: NavController, parkingPreferences: ParkingPreferences, paddingValues: PaddingValues) {
    var valorPorMinuto by remember { mutableStateOf(TextFieldValue("")) }
    var plazasDisponibles by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(top = 20.dp)) {
        Text(
            modifier = Modifier
                .padding(20.dp),

            text = "Configura tu Parkr",
            fontSize = 37.sp, // Tamaño de fuente
            fontWeight = FontWeight.Bold, // Texto en negrita
            color = Blue, // Color del texto
            style = androidx.compose.ui.text.TextStyle(letterSpacing = 2.sp),
            textAlign = TextAlign.Center)
    }
    Box(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
            .padding(paddingValues),
        contentAlignment = Alignment.Center // Centra vertical y horizontalmente
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(

                painter = painterResource(id = R.drawable.configura), // Reemplaza 'logo' con el nombre de tu imagen en drawable
                contentDescription = "Logo Parkr",
                modifier = Modifier
                    .size(300.dp) // Tamaño de la imagen
                    .padding(top = 5.dp)
                    .clip(CircleShape)



            )
            TextField(
                value = valorPorMinuto,
                onValueChange = { valorPorMinuto = it },
                label = { Text("Valor por minuto") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = 30.dp)// Opcional: Ajusta el ancho del campo de texto
            )
            TextField(
                value = plazasDisponibles,
                onValueChange = { plazasDisponibles = it },
                label = { Text("Cupos disponibles") },
                modifier = Modifier.fillMaxWidth(0.8f)
                    .padding(top = 20.dp)// Opcional: Ajusta el ancho del campo de texto
            )
            Button(
                onClick = {
                    // Guardar el valor por minuto y las plazas disponibles en SharedPreferences
                    parkingPreferences.saveConfig(valorPorMinuto.text.toInt(), plazasDisponibles.text.toInt())
                    Toast.makeText(navController.context, "Configuración Actualizada", Toast.LENGTH_SHORT)
                        .show()
                    navController.navigate(AppScreens.IngresoScreen.route)
                },
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text("Guardar Configuración")
            }
        }
    }
}

