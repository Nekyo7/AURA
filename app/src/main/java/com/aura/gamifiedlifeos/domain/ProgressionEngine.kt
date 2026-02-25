package com.aura.gamifiedlifeos.domain

import com.aura.gamifiedlifeos.data.model.MomentumState
import kotlin.math.roundToInt
import kotlin.random.Random

data class ActionResolutionInput(
    val baseAp: Int,
    val baseMb: Int,
    val difficulty: Int,
    val momentum: MomentumState,
    val isLowEnergyMode: Boolean,
    val streakDays: Int,
    val ccChancePercent: Float
)

data class ActionResolutionResult(
    val apAwarded: Int,
    val mbAwarded: Int,
    val ccAwarded: Int,
    val nextMomentum: MomentumState,
    val nextMomentumScore: Float,
    val levelAfter: Int
)

class ProgressionEngine(
    private val random: Random = Random.Default
) {
    fun resolveAction(
        input: ActionResolutionInput,
        currentAp: Int,
        currentMomentumScore: Float
    ): ActionResolutionResult {
        val normalizedDifficulty = input.difficulty.coerceIn(1, 5)
        val baseFactor = 0.8f + normalizedDifficulty * 0.15f
        val momentumMultiplier = when (input.momentum) {
            MomentumState.LOW -> 0.9f
            MomentumState.STABLE -> 1.05f
            MomentumState.FLOW -> 1.2f
        }
        val lowEnergyMultiplier = if (input.isLowEnergyMode) 0.85f else 1f
        val streakBonus = (input.streakDays.coerceAtMost(21) / 21f) * 0.2f

        val totalMultiplier = (baseFactor + streakBonus) * momentumMultiplier * lowEnergyMultiplier

        val apAward = (input.baseAp * totalMultiplier).roundToInt().coerceAtLeast(1)
        val mbAward = (input.baseMb * (0.75f + normalizedDifficulty * 0.1f) * lowEnergyMultiplier)
            .roundToInt()
            .coerceAtLeast(1)

        val nextScoreRaw = currentMomentumScore + (normalizedDifficulty * 4f) + if (input.isLowEnergyMode) 1.5f else 2.5f
        val nextMomentum = when {
            nextScoreRaw >= 72f -> MomentumState.FLOW
            nextScoreRaw >= 30f -> MomentumState.STABLE
            else -> MomentumState.LOW
        }

        val ccRoll = random.nextFloat() * 100f
        val ccAward = if (ccRoll <= input.ccChancePercent.coerceIn(0f, 100f)) 1 else 0
        val apAfter = currentAp + apAward

        return ActionResolutionResult(
            apAwarded = apAward,
            mbAwarded = mbAward,
            ccAwarded = ccAward,
            nextMomentum = nextMomentum,
            nextMomentumScore = nextScoreRaw.coerceAtMost(100f),
            levelAfter = toLevel(apAfter)
        )
    }

    fun applyMissedDayDecay(
        currentMomentumScore: Float,
        floor: Float,
        forgivenessUsed: Boolean
    ): Float {
        if (forgivenessUsed) return currentMomentumScore
        val decayed = currentMomentumScore * 0.93f
        return decayed.coerceAtLeast(floor)
    }

    fun toLevel(auraPoints: Int): Int {
        val xp = auraPoints.coerceAtLeast(0)
        return (1 + kotlin.math.sqrt(xp / 24f)).roundToInt().coerceAtLeast(1)
    }
}
