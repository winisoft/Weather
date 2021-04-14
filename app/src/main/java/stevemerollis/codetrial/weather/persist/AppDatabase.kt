package stevemerollis.codetrial.weather.persist

import androidx.room.Database
import androidx.room.RoomDatabase
import stevemerollis.codetrial.weather.conditions.ConditionCode
import stevemerollis.codetrial.weather.conditions.ConditionCodeDao

@Database(entities = [ConditionCode::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun conditionCodeDao(): ConditionCodeDao
}