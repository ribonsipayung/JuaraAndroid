package com.ribonsipayung.weatherinformation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.ribonsipayung.weatherinformation.ui.WeatherInformationApp
import com.ribonsipayung.weatherinformation.ui.theme.WeatherInformationTheme

// Kelas utama MainActivity yang merupakan turunan dari ComponentActivity.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengatur bahwa decor (elemen UI) tidak akan melihat ke dalam sistem windows.
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // Menentukan konten utama aplikasi menggunakan Compose.
        setContent {
            // Menggunakan tema aplikasi WeatherInformation.
            WeatherInformationTheme {
                // Menampilkan Surface (kontainer utama aplikasi) dengan konten aplikasi WeatherInformation.
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WeatherInformationApp()
                }
            }
        }
    }
}
