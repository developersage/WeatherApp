package com.example.weatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.repo.WeatherRepo
import com.example.weatherapp.repo.local.WeatherDao
import com.example.weatherapp.repo.local.WeatherDatabase
import com.example.weatherapp.repo.remote.WeatherService
import com.example.weatherapp.util.Constants
import com.example.weatherapp.view.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherModule {

    @Provides @Singleton
    fun provideWeatherService(): WeatherService = Retrofit.Builder()
        .baseUrl(Constants.BASE_WEATHER_API_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(WeatherService::class.java)

    @Provides @Singleton
    fun provideWeatherDatabase(
        @ApplicationContext appContext: Context
    ): WeatherDatabase = Room.databaseBuilder(
        appContext, WeatherDatabase::class.java, Constants.DATABASE_NAME
    ).build()

    @Provides @Singleton
    fun provideWeatherDao(weatherDatabase: WeatherDatabase) = weatherDatabase.weatherDao()

    @Provides @Singleton
    fun provideWeatherRepo(
        remote: WeatherService,
        local: WeatherDao
    ): WeatherRepo = WeatherRepo(remote, local)

}