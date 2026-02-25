package com.aura.gamifiedlifeos.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aura.gamifiedlifeos.data.model.MomentumState

@Composable
fun DashboardScreen(viewModel: DashboardViewModel = viewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF090B16), Color(0xFF121A2F), Color(0xFF090B16))
                )
            )
            .padding(16.dp)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item {
                Header(state)
            }
            item {
                Currencies(state)
            }
            item {
                MomentumCard(state)
            }
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Low-Energy Mode", style = MaterialTheme.typography.titleMedium)
                    Switch(
                        checked = state.isLowEnergyMode,
                        onCheckedChange = viewModel::toggleLowEnergyMode
                    )
                }
            }
            item {
                Text("Quick Actions", style = MaterialTheme.typography.titleMedium)
            }
            items(state.quickActions) { action ->
                QuickActionItem(action)
            }
        }
    }
}

@Composable
private fun Header(state: DashboardUiState) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "Welcome back, ${state.userName}", style = MaterialTheme.typography.titleLarge)
            Text(text = "Level ${state.level}", fontWeight = FontWeight.Bold)
            LinearProgressIndicator(
                progress = { state.auraPoints.toFloat() / state.auraToNextLevel.toFloat() },
                modifier = Modifier.fillMaxWidth()
            )
            Text("AP ${state.auraPoints} / ${state.auraToNextLevel}")
        }
    }
}

@Composable
private fun Currencies(state: DashboardUiState) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        StatChip("Mew Bucks", state.mewBucks.toString(), Modifier.weight(1f))
        StatChip("Chrono Chirals", state.chronoChirals.toString(), Modifier.weight(1f))
    }
}

@Composable
private fun StatChip(label: String, value: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(label, style = MaterialTheme.typography.labelLarge)
            Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun MomentumCard(state: DashboardUiState) {
    val glow = when (state.momentumState) {
        MomentumState.LOW -> Color(0xFFE3659F)
        MomentumState.STABLE -> Color(0xFF5AE3FF)
        MomentumState.FLOW -> Color(0xFF9AFF7A)
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Momentum: ${state.momentumState}", fontWeight = FontWeight.Bold, color = glow)
            LinearProgressIndicator(
                progress = { state.momentumScore / 100f },
                modifier = Modifier.fillMaxWidth(),
                color = glow
            )
            Text("${state.momentumScore.toInt()} / 100")
        }
    }
}

@Composable
private fun QuickActionItem(action: QuickActionCard) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(action.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(action.subtitle, style = MaterialTheme.typography.bodyMedium)
            Text("+${action.ap} AP  •  +${action.mb} MB", color = Color(0xFF6CF9E1))
        }
    }
}
