package com.lukman.data.network

import com.lukman.data.response.ForecastResponse
import com.lukman.data.response.WeatherResponse
import com.lukman.weather.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    fun weatherByCity(
        @Query("q") city : String,
        @Query("appid") apiKey: String? = BuildConfig.API_KEY
    ) : Call<WeatherResponse>
    @GET("forecast")
    fun forecastByCity(
        @Query("q") city : String,
        @Query("appid") apiKey: String? = BuildConfig.API_KEY
    ) : Call<ForecastResponse>
    @GET("weather")
    fun weatherByCurrentLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String? = BuildConfig.API_KEY
    ) : Call<WeatherResponse>
    @GET("forecast")
    fun forecstByCurrentLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String? = BuildConfig.API_KEY
    ) : Call<ForecastResponse>
}