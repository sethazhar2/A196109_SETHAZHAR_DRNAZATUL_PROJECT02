package com.example.a196109_sethazhar_drnazatul_project02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.example.a196109_sethazhar_drnazatul_project02.screens.*
import com.example.a196109_sethazhar_drnazatul_project02.ui.theme.A196109_SETHAZHAR_DRNAZATUL_PROJECT02Theme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A196109_SETHAZHAR_DRNAZATUL_PROJECT02Theme {
                HydroTrackApp()
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Stats : Screen("stats")
    object Profile : Screen("profile")
    object Summary : Screen("summary")
    object Tips : Screen("tips")
    object Weather : Screen("weather")
    object Location : Screen("location")
    object Community : Screen("community")
}

@Composable
fun HydroTrackApp() {

    val navController = rememberNavController()
    val viewModel: HydroViewModel = viewModel()

    val state by viewModel.state.collectAsStateWithLifecycle()
    val message by viewModel.message.collectAsStateWithLifecycle()
    val input by viewModel.input.collectAsStateWithLifecycle()
    val logs by viewModel.logs.collectAsStateWithLifecycle()
    val note by viewModel.note.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.weight(1f)
        ) {

            composable(Screen.Home.route) {
                MainDashboard(
                    waterInput = input,
                    message = message,
                    current = state.current,
                    onWaterChange = { viewModel.updateInput(it) },
                    onLogClick = { viewModel.logWater() },
                    onQuickAdd = { viewModel.quickAdd() },

                    // ✅ FIXED
                    noteInput = note,
                    onNoteChange = { viewModel.updateNote(it) }
                )
            }

            composable(Screen.Stats.route) {
                StatsScreen(state)
            }

            composable(Screen.Profile.route) {
                ProfileScreen(state)
            }

            composable(Screen.Summary.route) {
                SummaryScreen(
                    logs = logs,
                    onDelete = { viewModel.deleteLog(it) } ,
                    onEdit = { log, newNote ->
                        viewModel.updateLogNote(log, newNote)
                    }

                )
            }

            composable(Screen.Tips.route) {
                DailyTipsScreen()
            }
            composable(Screen.Weather.route) {
                WeatherScreen()
            }

            composable(Screen.Location.route) {
                LocationScreen()
            }

            composable(Screen.Community.route) {
                CommunityScreen()
            }
        }

        BottomNavBar(navController)
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.surface)
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        NavItem("Home") { navController.navigate(Screen.Home.route) }
        NavItem("Stats") { navController.navigate(Screen.Stats.route) }
        NavItem("Profile") { navController.navigate(Screen.Profile.route) }
        NavItem("Summary") { navController.navigate(Screen.Summary.route) }
        NavItem("Tips") { navController.navigate(Screen.Tips.route) }
        NavItem("Weather") { navController.navigate(Screen.Weather.route) }
        NavItem("Location") { navController.navigate(Screen.Location.route) }
        NavItem("Community") { navController.navigate(Screen.Community.route) }
    }
}

@Composable
fun NavItem(title: String, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Text(title, fontSize = 14.sp)
    }
}

@Composable
fun SmallCard(title: String, value: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title)
            Text(value)
        }
    }
}

@Composable
fun HistoryCard(time: String, amount: String) {

    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { expanded = !expanded },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(time)
                Text(amount)
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text("Hydration keeps you healthy 💧")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    A196109_SETHAZHAR_DRNAZATUL_PROJECT02Theme {
        HydroTrackApp()
    }
}