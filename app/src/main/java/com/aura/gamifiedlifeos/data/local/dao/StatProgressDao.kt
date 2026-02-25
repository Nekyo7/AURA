package com.aura.gamifiedlifeos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aura.gamifiedlifeos.data.local.entity.StatProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StatProgressDao {
    @Query("SELECT * FROM stat_progress WHERE userId = :userId ORDER BY statType")
    fun observeForUser(userId: Long = 1): Flow<List<StatProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(stats: List<StatProgressEntity>)
}
