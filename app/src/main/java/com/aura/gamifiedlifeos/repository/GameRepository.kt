package com.aura.gamifiedlifeos.repository

import com.aura.gamifiedlifeos.data.local.dao.ActionLogDao
import com.aura.gamifiedlifeos.data.local.dao.HabitActionDao
import com.aura.gamifiedlifeos.data.local.dao.StatProgressDao
import com.aura.gamifiedlifeos.data.local.dao.UserProfileDao
import com.aura.gamifiedlifeos.data.local.entity.UserProfileEntity
import kotlinx.coroutines.flow.Flow

class GameRepository(
    private val userProfileDao: UserProfileDao,
    private val statProgressDao: StatProgressDao,
    private val habitActionDao: HabitActionDao,
    private val actionLogDao: ActionLogDao
) {
    fun observeProfile(userId: Long = 1): Flow<UserProfileEntity?> = userProfileDao.observe(userId)

    suspend fun upsertProfile(profile: UserProfileEntity) = userProfileDao.upsert(profile)

    fun observeHabits(userId: Long = 1) = habitActionDao.observeActiveHabits(userId)

    fun observeRecentLogs(userId: Long = 1) = actionLogDao.observeRecent(userId)

    fun observeStats(userId: Long = 1) = statProgressDao.observeForUser(userId)
}
