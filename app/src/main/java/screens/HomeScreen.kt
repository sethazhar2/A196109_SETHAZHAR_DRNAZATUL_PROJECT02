package com.example.a196109_sethazhar_drnazatul_project02.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a196109_sethazhar_drnazatul_project02.HistoryCard
import com.example.a196109_sethazhar_drnazatul_project02.R
import com.example.a196109_sethazhar_drnazatul_project02.SmallCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.ui.layout.ContentScale

@Composable
fun MainDashboard(
    waterInput: String,
    message: String,
    current: Double,
    onWaterChange: (String) -> Unit,
    onLogClick: () -> Unit,
    noteInput: String,
    onNoteChange: (String) -> Unit,
    onQuickAdd: () -> Unit
) {

    val goal = 2.5
    val remaining = goal - current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {

        Text(
            text = "HydroTrack",
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.primary
        )

        // ✅ ADD YOUR MATRIC NUMBER HERE
        Text(
            text = "Matric No: A196109", // CHANGE THIS
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(16.dp))

        // MAIN CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Daily Water Intake")
                Text(
                    "${"%.1f".format(current)} L / $goal L",
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // STATS
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SmallCard("Today", "${"%.1f".format(current)} L")
            SmallCard("Remaining", "${"%.1f".format(remaining)} L")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text("Today's Progress", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(8.dp))

        LinearProgressIndicator(
            progress = { (current / goal).toFloat().coerceIn(0f, 1f) },
            modifier = Modifier.fillMaxWidth().height(20.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("${((current / goal) * 100).toInt().coerceAtMost(100)}% Complete")

        Spacer(modifier = Modifier.height(16.dp))




        // 🔥 INTERACTION SECTION
        Text("Log Your Water Intake", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            TextField(
                value = waterInput,
                onValueChange = onWaterChange,
                placeholder = { Text("Enter ml") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = onLogClick) {
                Text("Log")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = noteInput,
            onValueChange = onNoteChange,
            placeholder = { Text("Enter note ") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        // 🔄 DYNAMIC UI
        Text(
            text = message,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))



        // QUICK ADD BUTTON
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .shadow(8.dp, CircleShape)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        CircleShape
                    )
                    .clickable { onQuickAdd() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "💧",
                    fontSize = 32.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Quick Add 250ml",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}



