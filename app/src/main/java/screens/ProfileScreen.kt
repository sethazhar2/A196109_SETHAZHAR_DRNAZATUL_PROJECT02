package com.example.a196109_sethazhar_drnazatul_project02.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a196109_sethazhar_drnazatul_project02.HydrationData
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.a196109_sethazhar_drnazatul_project02.R

@Composable
fun ProfileScreen(data: HydrationData) {

    val progress = (data.current / data.goal).coerceIn(0.0, 1.0)
    val percentage = (progress * 100).toInt()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier.size(120.dp),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.myphoto),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(data.name, fontSize = 26.sp)
        Text("HydroTrack User", color = MaterialTheme.colorScheme.onSurfaceVariant)

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Personal Hydration Goal", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("🎯 Daily Goal: ${"%.1f".format(data.goal)} L")
                Text("💧 Current Intake: ${"%.1f".format(data.current)} L")
                Text("📊 Progress: $percentage%")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("App Purpose", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("HydroTrack helps users monitor daily water intake and build healthier hydration habits.")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("SDG Connection", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("This app supports good health and well-being by encouraging good hydration.")
            }
        }
    }
}

