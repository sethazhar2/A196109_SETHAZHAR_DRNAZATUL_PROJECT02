package com.example.a196109_sethazhar_drnazatul_project02.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a196109_sethazhar_drnazatul_project02.HydrationData
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.a196109_sethazhar_drnazatul_project02.R
import androidx.compose.ui.layout.ContentScale
@Composable
fun StatsScreen(data: HydrationData) {

    val progress = (data.current / data.goal).coerceIn(0.0, 1.0)
    val percentage = (progress * 100).toInt()
    val remaining = (data.goal - data.current).coerceAtLeast(0.0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Hydration Stats", fontSize = 28.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Today's Progress", fontSize = 20.sp)

                Spacer(modifier = Modifier.height(12.dp))

                LinearProgressIndicator(
                    progress = progress.toFloat(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(12.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("$percentage% completed")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("💧 Current Intake: ${"%.1f".format(data.current)} L")
                Text("🎯 Daily Goal: ${"%.1f".format(data.goal)} L")
                Text("🚰 Remaining: ${"%.1f".format(remaining)} L")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Hydration Status", fontSize = 20.sp)

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = when {
                        percentage >= 100 -> "🎉 Goal achieved! Great job."
                        percentage >= 70 -> "🟢 Well hydrated. Keep it up!"
                        percentage >= 30 -> "🟡 Moderate progress. Drink more water."
                        else -> "🔴 Low intake. Start drinking water now."
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.water7),
                contentDescription = "Hydration Statistics",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
        }
    }
}