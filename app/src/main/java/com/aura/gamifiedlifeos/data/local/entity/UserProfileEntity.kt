package com.aura.gamifiedlifeos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aura.gamifiedlifeos.data.model.MomentumState

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Long = 1,
    val displayName: String,
    val avatarUri: String?,
    val level: Int = 1,
    val auraPoints: Int = 0,
    val mewBucks: Int = 0,
    val chronoChirals: Int = 0,
    val momentumScore: Float = 0f,
    val momentumState: MomentumState = MomentumState.LOW,
    val momentumFloor: Float = 10f,
    val streakDays: Int = 0,
    val snoozePasses: Int = 0,
    val updatedAtEpochMs: Long
)
