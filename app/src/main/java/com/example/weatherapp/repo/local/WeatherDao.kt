package com.example.weatherapp.repo.local

import androidx.room.*
import com.example.weatherapp.model.WeatherData
import com.example.weatherapp.util.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
    fun getAllData(): Flow<List<WeatherData>>

//    //Only necessary for compose UI
//    @Query("SELECT * FROM ${Constants.TABLE_NAME}")
//    fun getFirstData(): Flow<WeatherData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(weather: WeatherData)

    @Delete
    fun deleteData(weather: WeatherData)

//    //Not needed.
//    @Query("DELETE FROM ${Constants.TABLE_NAME}")
//    fun deleteAllData()

}