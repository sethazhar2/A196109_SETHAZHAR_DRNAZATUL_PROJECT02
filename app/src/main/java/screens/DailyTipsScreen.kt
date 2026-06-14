package com.example.a196109_sethazhar_drnazatul_project02.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DailyTipsScreen() {

    val tips = listOf(
        "Drink a glass of water after waking up 💧",
        "Carry a water bottle everywhere 🚶‍♂️",
        "Drink before you feel thirsty ⚠️",
        "Avoid sugary drinks 🍹",
        "Increase water intake during exercise 🏃‍♂️"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Daily Hydration Tips",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        tips.forEach { tip ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {

                Text(
                    text = tip,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp
                )
            }
        }
    }
}


