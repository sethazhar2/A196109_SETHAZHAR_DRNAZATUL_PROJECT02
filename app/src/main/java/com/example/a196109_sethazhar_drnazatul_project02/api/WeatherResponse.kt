package com.example.a196109_sethazhar_drnazatul_project02.api

data class WeatherResponse(
    val current: CurrentWeather
)

data class CurrentWeather(
    val temperature_2m: Double,
    val wind_speed_10m: Double
)