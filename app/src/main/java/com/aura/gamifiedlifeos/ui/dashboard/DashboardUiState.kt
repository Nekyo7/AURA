package com.aura.gamifiedlifeos.ui.dashboard

import com.aura.gamifiedlifeos.data.model.MomentumState

data class QuickActionCard(
    val title: String,
    val subtitle: String,
    val ap: Int,
    val mb: Int
)

data class DashboardUiState(
    val userName: String = "Runner",
    val level: Int = 7,
    val auraPoints: Int = 580,
    val auraToNextLevel: Int = 720,
    val mewBucks: Int = 164,
    val chronoChirals: Int = 3,
    val momentumState: MomentumState = MomentumState.STABLE,
    val momentumScore: Float = 46f,
    val isLowEnergyMode: Boolean = false,
    val quickActions: List<QuickActionCard> = listOf(
        QuickActionCard("Deep Work", "25 min focus sprint", ap = 35, mb = 16),
        QuickActionCard("Body Reset", "Hydrate + stretch", ap = 20, mb = 9),
        QuickActionCard("Micro-Chore", "2 min cleanup", ap = 14, mb = 8)
    )
)
