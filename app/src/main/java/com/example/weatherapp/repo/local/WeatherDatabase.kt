package com.example.weatherapp.repo.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.model.WeatherData

@Database(entities = [WeatherData::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}