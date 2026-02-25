package com.aura.gamifiedlifeos.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.aura.gamifiedlifeos.data.model.StatType

@Entity(
    tableName = "habit_action",
    foreignKeys = [
        ForeignKey(
            entity = UserProfileEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("userId")]
)
data class HabitActionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val title: String,
    val description: String,
    val difficulty: Int,
    val energyCost: Int,
    val isLowEnergyFriendly: Boolean,
    val primaryStat: StatType,
    val baseApReward: Int,
    val baseMbReward: Int,
    val baseCcChancePercent: Float,
    val isArchived: Boolean = false,
    val createdAtEpochMs: Long
)
