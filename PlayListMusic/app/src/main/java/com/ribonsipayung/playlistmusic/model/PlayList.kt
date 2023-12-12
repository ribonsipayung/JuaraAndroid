package com.ribonsipayung.playlistmusic.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class PlayList(
    @StringRes val name: Int,
    val availableCourses: Int,
    @DrawableRes val imageRes: Int
)
