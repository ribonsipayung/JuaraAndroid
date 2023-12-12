package com.ribonsipayung.weatherinformation.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ribonsipayung.weatherinformation.network.OpenWeatherMapApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// Interface untuk mendefinisikan status tampilan layar cuaca.
sealed interface WeatherUiState {
    data class Success(val weatherInfo: String) : WeatherUiState
    object Error : WeatherUiState
    object Loading : WeatherUiState
}

// Kelas ViewModel untuk layar cuaca.
class WeatherViewModel : ViewModel() {
    // Mutable state yang merepresentasikan status tampilan cuaca.
    var weatherUiState: WeatherUiState by mutableStateOf(WeatherUiState.Loading)
        private set

    // Inisialisasi ViewModel dengan kota default (Jakarta) untuk data cuaca.
    init {
        val defaultCity = "Jakarta"
        getWeatherData(defaultCity)
    }

    // Fungsi untuk mendapatkan data cuaca berdasarkan kota.
    private fun getWeatherData(city: String) {
        viewModelScope.launch {
            // Mengubah status menjadi Loading saat pengambilan data dimulai.
            weatherUiState = WeatherUiState.Loading
            // Mengubah status menjadi Success atau Error berdasarkan hasil pengambilan data.
            weatherUiState = try {
                val weatherResult = OpenWeatherMapApi.retrofitService.getWeather(city)
                WeatherUiState.Success(
                    "Temperature in $city: ${weatherResult.main.temp}°C"
                )
            } catch (e: IOException) {
                WeatherUiState.Error
            } catch (e: HttpException) {
                WeatherUiState.Error
            }
        }
    }
}


