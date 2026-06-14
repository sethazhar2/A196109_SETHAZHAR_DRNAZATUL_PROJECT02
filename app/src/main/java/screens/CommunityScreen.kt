package com.example.a196109_sethazhar_drnazatul_project02.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a196109_sethazhar_drnazatul_project02.CommunityPost
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun CommunityScreen() {

    var message by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }
    var tips by remember { mutableStateOf(listOf<String>()) }

    LaunchedEffect(Unit) {
        Firebase.firestore
            .collection("hydrationTips")
            .get()
            .addOnSuccessListener { result ->
                tips = result.documents.mapNotNull {
                    it.getString("message")
                }
            }
            .addOnFailureListener { e ->
                status = "Failed to load tips: ${e.message}"
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Community Hydration Tips",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                Text("Share Your Tip", fontSize = 20.sp)

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = message,
                    onValueChange = { message = it },
                    label = { Text("Share a hydration tip") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        if (message.isBlank()) {
                            status = "Please enter a tip first"
                            return@Button
                        }

                        status = "Uploading..."

                        val post = CommunityPost(message)

                        Firebase.firestore
                            .collection("hydrationTips")
                            .add(post)
                            .addOnSuccessListener {
                                status = "Tip uploaded successfully ✅"
                                tips = tips + message
                                message = ""
                            }
                            .addOnFailureListener { e ->
                                status = "Upload failed: ${e.message}"
                            }
                    }
                ) {
                    Text("Upload Tip")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(status)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Community Tips", fontSize = 20.sp)

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(tips) { tip ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Text(
                        text = "💧 $tip",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}