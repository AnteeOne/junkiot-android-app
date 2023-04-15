package tech.antee.junkiot.main.impl.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class NavigationItem(
    @DrawableRes val iconId: Int,
    @StringRes val titleId: Int,
    val route: String
)
