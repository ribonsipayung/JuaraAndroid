package com.ribonsipayung.weatherinformation.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Ini adalah definisi data class WeatherInformation.
 */
@Serializable
data class WeatherInformation(
    val id: String,
    @SerialName(value = "img_src")
    val imgSrc: String
)
