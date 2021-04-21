package stevemerollis.codetrial.weather.persist

import androidx.room.Database
import androidx.room.RoomDatabase
import stevemerollis.codetrial.weather.api.model.ConditionCode

@Database(entities = [ConditionCode::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun conditionCodeDao(): ConditionCodeDao

    companion object {
        const val NAME = "weather_db"
    }
}