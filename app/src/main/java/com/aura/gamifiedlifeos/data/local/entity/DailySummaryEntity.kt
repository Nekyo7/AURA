package com.aura.gamifiedlifeos.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "daily_summary",
    primaryKeys = ["userId", "dayEpoch"],
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
data class DailySummaryEntity(
    val userId: Long,
    val dayEpoch: Long,
    val completedActions: Int,
    val totalAp: Int,
    val totalMb: Int,
    val totalCc: Int,
    val missedPenaltyApplied: Boolean,
    val forgivenessUsed: Boolean
)
