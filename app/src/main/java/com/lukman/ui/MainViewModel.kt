package com.lukman.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lukman.data.response.WeatherResponse
import com.lukman.data.network.ApiConfig
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel(){

    private val weatherByCity = MutableLiveData<WeatherResponse>()

    fun searchByCity(city: String) {
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

    fun getWeatherByCity() : LiveData<WeatherResponse> = weatherByCity
}