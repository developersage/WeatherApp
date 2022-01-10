package com.example.weatherapp.model

import com.squareup.moshi.Json

data class WeatherJson(
    val location: WeatherLocation,
    val current: WeatherCurrent,
    //val forecast: WeatherForecast
)

data class WeatherLocation(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    val tz_id: String,
    @Json(name = "localtime_epoch")
    val localtimeEpoch: Int,
    val localtime: String
)

data class WeatherCurrent(
    @Json(name = "last_updated_epoch") val lastUpdatedEpoch: Int,
    @Json(name = "last_updated") val lastUpdated: String,
    @Json(name = "temp_c") val tempC: Double,
    @Json(name = "temp_f") val tempF: Double,
    @Json(name = "is_day") val isDay: Int,
    val condition: WeatherCondition,
    @Json(name = "wind_mph") val windMph: Double,
    @Json(name = "wind_kph") val windKph: Double,
    @Json(name = "wind_degree") val windDegree: Int,
    @Json(name = "wind_dir") val windDir: String,
    @Json(name = "pressure_mb") val pressureMb: Double,
    @Json(name = "pressure_in") val pressureIn: Double,
    @Json(name = "precip_mm") val precipMm: Double,
    @Json(name = "precip_in") val precipIn: Double,
    val humidity: Int,
    val cloud: Int,
    @Json(name = "feelslike_c") val feelslikeC: Double,
    @Json(name = "feelslike_f") val feelslikeF: Double,
    @Json(name = "vis_km") val visKm: Double,
    @Json(name = "vis_miles") val visMiles: Double,
    val uv: Double,
    @Json(name = "gust_mph") val gustMph: Double,
    @Json(name = "gust_kph") val gustKph: Double,
)

data class WeatherCondition(
    val text: String,
    val icon: String,
    val code: Int
)

data class Hour(
    val chance_of_rain: Int,
    val chance_of_snow: Int,
    val cloud: Int,
    val condition: Condition,
    val dewpoint_c: Double,
    val dewpoint_f: Double,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val gust_kph: Double,
    val gust_mph: Double,
    val heatindex_c: Double,
    val heatindex_f: Double,
    val humidity: Int,
    val is_day: Int,
    val precip_in: Double,
    val precip_mm: Double,
    val pressure_in: Double,
    val pressure_mb: Double,
    val temp_c: Double,
    val temp_f: Double,
    val time: String,
    val time_epoch: Int,
    val uv: Double,
    val vis_km: Double,
    val vis_miles: Double,
    val will_it_rain: Int,
    val will_it_snow: Int,
    val wind_degree: Int,
    val wind_dir: String,
    val wind_kph: Double,
    val wind_mph: Double,
    val windchill_c: Double,
    val windchill_f: Double
)

data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)

//data class WeatherForecast(
//
//)

/*
* {
    "location": {
        "name": "Los Angeles",
        "region": "California",
        "country": "USA",
        "lat": 34.06,
        "lon": -118.31,
        "tz_id": "America/Los_Angeles",
        "localtime_epoch": 1641490674,
        "localtime": "2022-01-06 9:37"
    },
    "current": {
        "last_updated_epoch": 1641490200,
        "last_updated": "2022-01-06 09:30",
        "temp_c": 13.3,
        "temp_f": 55.9,
        "is_day": 1,
        "condition": {
            "text": "Sunny",
            "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png",
            "code": 1000
        },
        "wind_mph": 0.0,
        "wind_kph": 0.0,
        "wind_degree": 0,
        "wind_dir": "N",
        "pressure_mb": 1016.0,
        "pressure_in": 30.0,
        "precip_mm": 0.0,
        "precip_in": 0.0,
        "humidity": 64,
        "cloud": 0,
        "feelslike_c": 13.8,
        "feelslike_f": 56.8,
        "vis_km": 13.0,
        "vis_miles": 8.0,
        "uv": 4.0,
        "gust_mph": 5.1,
        "gust_kph": 8.3
    }
}
*
* */