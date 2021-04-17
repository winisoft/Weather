package stevemerollis.codetrial.weather.persist

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import stevemerollis.codetrial.weather.api.model.ConditionCode

@Dao
interface ConditionCodeDao {

    @Query("SELECT * FROM conditionCode")
    fun getAll(): List<ConditionCode>

    @Query("SELECT * FROM conditionCode WHERE id IN (:conditionCodes)")
    fun getAllByIds(conditionCodes: IntArray): List<ConditionCode>

    @Update(entity = ConditionCode::class)
    fun update(entity: ConditionCode)

    @Update(entity = ConditionCode::class)
    fun updateAll(entities: List<ConditionCode>)
}