package com.example.a196109_sethazhar_drnazatul_project02.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import android.location.Geocoder
import java.util.Locale

@SuppressLint("MissingPermission")
@Composable
fun LocationScreen() {

    val context = LocalContext.current
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    var locationText by remember {
        mutableStateOf("Press the button to get your current location")
    }

    var statusText by remember {
        mutableStateOf("GPS sensor is ready")
    }

    fun getLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {

                val geocoder = Geocoder(context, Locale.getDefault())

                val addresses = geocoder.getFromLocation(
                    location.latitude,
                    location.longitude,
                    1
                )

                val locationName =
                    addresses?.firstOrNull()?.locality
                        ?: addresses?.firstOrNull()?.subAdminArea
                        ?: addresses?.firstOrNull()?.adminArea
                        ?: "Unknown Location"

                locationText =
                    "📍 $locationName\n\nLatitude: ${"%.5f".format(location.latitude)}\nLongitude: ${"%.5f".format(location.longitude)}"

                statusText = "✅ Location detected successfully"

            } else {
                locationText = "Location is null. Turn on GPS and try again."
                statusText = "⚠️ GPS data unavailable"
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            getLocation()
        } else {
            locationText = "Location permission denied"
            statusText = "❌ Permission required to use GPS sensor"
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("📍", fontSize = 52.sp)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Location Sensor",
            style = MaterialTheme.typography.headlineMedium
        )

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
                Text("Current GPS Location", fontSize = 20.sp)

                Spacer(modifier = Modifier.height(12.dp))

                Text(locationText)

                Spacer(modifier = Modifier.height(12.dp))

                Text(statusText)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Hydration Use", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Your location can be used with weather data to suggest better hydration habits.")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val permissionCheck = ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    getLocation()
                } else {
                    permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
        ) {
            Text("Get Current Location")
        }
    }
}