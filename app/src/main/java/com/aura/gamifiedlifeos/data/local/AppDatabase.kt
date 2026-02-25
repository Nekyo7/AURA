package com.aura.gamifiedlifeos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aura.gamifiedlifeos.data.local.dao.ActionLogDao
import com.aura.gamifiedlifeos.data.local.dao.DailySummaryDao
import com.aura.gamifiedlifeos.data.local.dao.HabitActionDao
import com.aura.gamifiedlifeos.data.local.dao.StatProgressDao
import com.aura.gamifiedlifeos.data.local.dao.UserProfileDao
import com.aura.gamifiedlifeos.data.local.entity.ActionLogEntity
import com.aura.gamifiedlifeos.data.local.entity.DailySummaryEntity
import com.aura.gamifiedlifeos.data.local.entity.HabitActionEntity
import com.aura.gamifiedlifeos.data.local.entity.StatProgressEntity
import com.aura.gamifiedlifeos.data.local.entity.UserProfileEntity

@Database(
    entities = [
        UserProfileEntity::class,
        StatProgressEntity::class,
        HabitActionEntity::class,
        ActionLogEntity::class,
        DailySummaryEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProfileDao(): UserProfileDao
    abstract fun statProgressDao(): StatProgressDao
    abstract fun habitActionDao(): HabitActionDao
    abstract fun actionLogDao(): ActionLogDao
    abstract fun dailySummaryDao(): DailySummaryDao
}
