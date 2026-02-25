package com.aura.gamifiedlifeos.data.local

import androidx.room.TypeConverter
import com.aura.gamifiedlifeos.data.model.MomentumState
import com.aura.gamifiedlifeos.data.model.StatType

class Converters {
    @TypeConverter
    fun fromMomentum(value: MomentumState): String = value.name

    @TypeConverter
    fun toMomentum(value: String): MomentumState = MomentumState.valueOf(value)

    @TypeConverter
    fun fromStatType(value: StatType): String = value.name

    @TypeConverter
    fun toStatType(value: String): StatType = StatType.valueOf(value)
}
