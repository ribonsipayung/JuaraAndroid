package com.ribonsipayung.weatherinformation.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

// Interface untuk mendefinisikan layanan API OpenWeatherMap.
interface OpenWeatherMapService {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = "b92d6d36572ad121ddcac9d7c4030521"
    ): WeatherResponse
}

// Objek singleton untuk mengakses layanan Retrofit.
object OpenWeatherMapApi {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val retrofitService: OpenWeatherMapService by lazy {
        retrofit.create(OpenWeatherMapService::class.java)
    }
}

// Data class untuk menyimpan respons JSON cuaca.
data class WeatherResponse(val main: Main)

// Data class untuk menyimpan informasi suhu utama.
data class Main(val temp: Double)