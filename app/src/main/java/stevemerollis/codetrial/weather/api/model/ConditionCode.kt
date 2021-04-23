package stevemerollis.codetrial.weather.api.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "conditionCode")
data class ConditionCode(
    @PrimaryKey val id: Int,
    val main: String,
    val description: String,
    val icon: String?
)