package com.lukman.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukman.data.response.WeatherResponse
import com.lukman.data.network.ApiConfig
import com.lukman.data.response.ForecastResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val weatherByCity = MutableLiveData<WeatherResponse>()
    private val forecastByCity = MutableLiveData<ForecastResponse>()

    private val weatherByCurrentLocation = MutableLiveData<WeatherResponse>()
    private val forecastByCurrentLocation = MutableLiveData<ForecastResponse>()

    fun weatherByCity(city: String) {
        ApiConfig().getApiService().weatherByCity(city)
            .enqueue(object : retrofit2.Callback<WeatherResponse> {
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if (response.isSuccessful) weatherByCity.postValue(response.body())
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.e("FaillureCallApi", t.message.toString())
                }
            })
    }

    fun getWeatherByCity(): LiveData<WeatherResponse> = weatherByCity

    fun forecastByCity(city: String) {
        ApiConfig().getApiService().forecastByCity(city)
            .enqueue(object : Callback<ForecastResponse> {

                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    if (response.isSuccessful) forecastByCity.postValue(response.body())
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            })
    }

    fun getForecastByCity(): LiveData<ForecastResponse> = forecastByCity

    fun weatherByCurrentLocation(lat: Double, lon: Double) {
        ApiConfig().getApiService().weatherByCurrentLocation(lat, lon).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                weatherByCurrentLocation.postValue(response.body())
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getWeatherByCurrentLocation() : LiveData<WeatherResponse> = weatherByCurrentLocation

    fun forecastByCurrentLocation(lat: Double, lon: Double) {
        ApiConfig().getApiService().forecstByCurrentLocation(lat, lon)
            .enqueue(object : Callback<ForecastResponse> {
                override fun onResponse(
                    call: Call<ForecastResponse>,
                    response: Response<ForecastResponse>
                ) {
                    if (response.isSuccessful) forecastByCurrentLocation.postValue(response.body())
                }

                override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    fun getForecastWeatherByLocation(): LiveData<ForecastResponse> = forecastByCurrentLocation
}