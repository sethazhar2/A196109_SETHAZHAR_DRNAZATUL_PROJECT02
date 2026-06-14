package com.example.a196109_sethazhar_drnazatul_project02.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a196109_sethazhar_drnazatul_project02.WaterLogEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SummaryScreen(
    logs: List<WaterLogEntity>,
    onDelete: (WaterLogEntity) -> Unit,
    onEdit: (WaterLogEntity, String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Water Logs", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(logs) { log ->

                var isEditing by remember { mutableStateOf(false) }
                var editedNote by remember { mutableStateOf(log.note) }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {

                    Column(modifier = Modifier.padding(16.dp)) {

                        // TIME + AMOUNT
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                SimpleDateFormat("hh:mm a", Locale.getDefault())
                                    .format(Date(log.time.toLong()))
                            )

                            Text(
                                text = "${log.amount} ml",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        // NOTE SECTION
                        if (isEditing) {

                            TextField(
                                value = editedNote,
                                onValueChange = { editedNote = it },
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = { Text("Edit note") }
                            )

                            Row {

                                Button(
                                    onClick = {
                                        onEdit(log, editedNote)
                                        isEditing = false
                                    }
                                ) {
                                    Text("Save")
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                TextButton(
                                    onClick = {
                                        editedNote = log.note
                                        isEditing = false
                                    }
                                ) {
                                    Text("Cancel")
                                }
                            }

                        } else {

                            if (log.note.isNotBlank()) {
                                Text(log.note)
                            }

                            Row {

                                TextButton(
                                    onClick = { isEditing = true }
                                ) {
                                    Text("✏ Edit")
                                }

                                TextButton(
                                    onClick = { onDelete(log) }
                                ) {
                                    Text("🗑 Delete")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}