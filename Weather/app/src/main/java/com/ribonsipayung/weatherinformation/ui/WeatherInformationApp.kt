@file:OptIn(ExperimentalMaterial3Api::class)
package com.ribonsipayung.weatherinformation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ribonsipayung.weatherinformation.R
import com.ribonsipayung.weatherinformation.ui.screens.HomeScreen
import com.ribonsipayung.weatherinformation.ui.screens.WeatherViewModel

// Fungsi utama untuk menampilkan aplikasi WeatherInformation.
@Composable
fun WeatherInformationApp() {
    // Mendapatkan perilaku gulir default untuk TopAppBar.
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    // Menampilkan tampilan utama aplikasi menggunakan Scaffold.
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { WeatherTopAppBar(scrollBehavior = scrollBehavior) }
    ) {
        // Menampilkan konten utama aplikasi menggunakan Surface dan HomeScreen.
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            // Mendapatkan ViewModel untuk layar cuaca.
            val weatherViewModel: WeatherViewModel = viewModel()
            // Menampilkan layar utama aplikasi.
            HomeScreen(weatherUiState = weatherViewModel.weatherUiState)
        }
    }
}

// Fungsi untuk menampilkan TopAppBar khusus aplikasi cuaca.
@Composable
fun WeatherTopAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier) {
    // Menampilkan TopAppBar dengan posisi tengah dan judul aplikasi.
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}