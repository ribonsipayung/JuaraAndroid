package com.ribonsipayung.weatherinformation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ribonsipayung.weatherinformation.R
import com.ribonsipayung.weatherinformation.ui.theme.WeatherInformationTheme

// Fungsi untuk menampilkan layar utama berdasarkan status cuaca.
@Composable
fun HomeScreen(
    weatherUiState: WeatherUiState, modifier: Modifier = Modifier
) {
    when (weatherUiState) {
        is WeatherUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is WeatherUiState.Success -> ResultScreen(
            weatherUiState.weatherInfo, modifier = modifier.fillMaxWidth()
        )

        is WeatherUiState.Error -> ErrorScreen( modifier = modifier.fillMaxSize())
    }
}

/**
 * Fungsi untuk menampilkan layar loading.
 */
@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

/**
 * Fungsi untuk menampilkan layar dengan pesan error dan tombol coba lagi.
 */
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
    }
}

/**
 * Fungsi untuk menampilkan layar dengan informasi cuaca.
 */
@Composable
fun ResultScreen(weatherInfo: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = weatherInfo)
    }
}

// Preview untuk tampilan loading.
@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    WeatherInformationTheme {
        LoadingScreen()
    }
}

// Preview untuk tampilan error.
@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    WeatherInformationTheme {
        ErrorScreen()
    }
}

// Preview untuk tampilan hasil cuaca.
@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    WeatherInformationTheme {
        ResultScreen(stringResource(R.string.placeholder_success))
    }
}