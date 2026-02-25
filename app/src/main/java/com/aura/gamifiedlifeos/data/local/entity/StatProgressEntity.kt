package com.aura.gamifiedlifeos.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.aura.gamifiedlifeos.data.model.StatType

@Entity(
    tableName = "stat_progress",
    foreignKeys = [
        ForeignKey(
            entity = UserProfileEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("userId"), Index(value = ["userId", "statType"], unique = true)]
)
data class StatProgressEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val statType: StatType,
    val points: Int = 0,
    val level: Int = 1,
    val lastIncreasedAtEpochMs: Long
)
