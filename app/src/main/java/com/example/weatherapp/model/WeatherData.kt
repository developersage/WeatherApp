package com.example.weatherapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.util.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = Constants.TABLE_NAME)
data class WeatherData(
    @ColumnInfo(name = "city") val name: String,
    @ColumnInfo(name = "state") val region: String,
    @ColumnInfo(name = "lat") val latitude: Double,
    @ColumnInfo(name = "lon") val longitude: Double,
    @ColumnInfo(name = "temp_c") val tempC: Double,
    @ColumnInfo(name = "temp_f") val tempF: Double
): Parcelable {
    @PrimaryKey(autoGenerate = true) var id: Int = 0

    companion object{
        fun convertFromAPI(api: WeatherJson) = WeatherData(
            name = api.location.name,
            region = api.location.region,
            latitude = api.location.lat,
            longitude = api.location.lon,
            tempC = api.current.tempC,
            tempF = api.current.tempF
        )
    }
}