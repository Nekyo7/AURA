package com.aura.gamifiedlifeos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aura.gamifiedlifeos.data.local.entity.ActionLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ActionLogDao {
    @Query("SELECT * FROM action_log WHERE userId = :userId ORDER BY completedAtEpochMs DESC LIMIT :limit")
    fun observeRecent(userId: Long = 1, limit: Int = 50): Flow<List<ActionLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: ActionLogEntity)
}
