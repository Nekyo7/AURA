package com.aura.gamifiedlifeos.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.aura.gamifiedlifeos.data.model.MomentumState
import com.aura.gamifiedlifeos.data.model.StatType

@Entity(
    tableName = "action_log",
    foreignKeys = [
        ForeignKey(
            entity = UserProfileEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = HabitActionEntity::class,
            parentColumns = ["id"],
            childColumns = ["habitId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("userId"), Index("habitId"), Index("completedAtEpochMs")]
)
data class ActionLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val habitId: Long?,
    val titleSnapshot: String,
    val difficultySnapshot: Int,
    val statAwarded: StatType,
    val apAwarded: Int,
    val mbAwarded: Int,
    val ccAwarded: Int,
    val momentumBefore: MomentumState,
    val momentumAfter: MomentumState,
    val completedAtEpochMs: Long
)
