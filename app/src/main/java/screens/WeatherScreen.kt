package com.example.a196109_sethazhar_drnazatul_project02.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a196109_sethazhar_drnazatul_project02.api.RetrofitInstance

@Composable
fun WeatherScreen() {

    var temp by remember { mutableStateOf<Double?>(null) }
    var wind by remember { mutableStateOf<Double?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitInstance.api.getWeather(
                latitude = 3.14,
                longitude = 101.69
            )

            temp = response.current.temperature_2m
            wind = response.current.wind_speed_10m

        } catch (e: Exception) {
            error = e.message
        }
    }

    val advice = when {
        temp == null -> "Loading weather data..."
        temp!! >= 32 -> "Hot weather today. Drink more water to stay hydrated."
        temp!! >= 28 -> "Warm weather. Keep your water bottle nearby."
        else -> "Normal weather. Maintain your hydration habit."
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("Weather Hydration Reminder", fontSize = 26.sp)

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text("☀️", fontSize = 48.sp)

                Spacer(modifier = Modifier.height(8.dp))

                if (error != null) {
                    Text("Error: $error")
                } else {
                    Text(
                        text = temp?.let { "${it}°C" } ?: "Loading...",
                        fontSize = 36.sp
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = wind?.let { "Wind Speed: ${it} km/h" } ?: "",
                        fontSize = 18.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Hydration Advice", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(advice)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Source: Open-Meteo API",
            style = MaterialTheme.typography.bodySmall
        )
    }
}