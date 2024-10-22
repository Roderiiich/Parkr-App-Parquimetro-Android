package com.example.pruebas


import android.content.Context
import android.content.SharedPreferences

class ParkingPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("parking_prefs", Context.MODE_PRIVATE)

    fun saveVehicle(patente: String, fechaHora: String) {
        sharedPreferences.edit().putString(patente, fechaHora).apply()
    }

    fun getVehicleDetails(patente: String): Pair<String, String?> {
        val fechaHora = sharedPreferences.getString(patente, "")
        return Pair(fechaHora ?: "", patente)
    }

    fun removeVehicle(patente: String) {
        sharedPreferences.edit().remove(patente).apply()
    }

    fun saveConfig(valorPorMinuto: Int, plazasDisponibles: Int) {
        sharedPreferences.edit().putInt("valor_por_minuto", valorPorMinuto).apply()
        sharedPreferences.edit().putInt("plazas_disponibles", plazasDisponibles).apply()
    }

    fun getValorPorMinuto(): Int {
        return sharedPreferences.getInt("valor_por_minuto", 0)
    }
    fun getAllVehicles(): List<Pair<String, String>> {
        val allEntries = sharedPreferences.all
        return allEntries.mapNotNull { entry ->
            val patente = entry.key
            val fechaHora = entry.value as? String
            if (fechaHora != null) Pair(patente, fechaHora) else null
        }
    }

    fun getPlazasDisponibles(): Int {
        return sharedPreferences.getInt("plazas_disponibles", 0)
    }
}