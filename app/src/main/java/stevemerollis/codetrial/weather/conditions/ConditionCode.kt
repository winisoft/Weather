package stevemerollis.codetrial.weather.conditions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "conditionCode")
data class ConditionCode(
    @PrimaryKey val id: Int,
    val main: String,
    val description: String,
    val icon: String?
) {
}