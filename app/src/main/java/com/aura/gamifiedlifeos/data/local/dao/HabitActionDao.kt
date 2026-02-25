package com.aura.gamifiedlifeos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aura.gamifiedlifeos.data.local.entity.HabitActionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitActionDao {
    @Query("SELECT * FROM habit_action WHERE userId = :userId AND isArchived = 0 ORDER BY createdAtEpochMs DESC")
    fun observeActiveHabits(userId: Long = 1): Flow<List<HabitActionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(habit: HabitActionEntity): Long
}
