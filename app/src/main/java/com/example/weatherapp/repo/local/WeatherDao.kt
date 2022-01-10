package com.example.weatherapp.repo.local

import androidx.room.*
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
    fun getAllData(): Flow<List<WeatherData>>

    @Query("SELECT * FROM ${Constants.TABLE_NAME} WHERE id = :weather_id")
    fun getData(weather_id: Int): Flow<WeatherData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(weather: WeatherData)

    @Delete
    fun deleteData(weather: WeatherData)

    @Query("DELETE FROM ${Constants.TABLE_NAME}")
    fun deleteAllData()

}