package ru.gorshenev.test.presentation.menu_screen

import androidx.annotation.StringRes
import java.io.Serializable

sealed class ScreenType : Serializable {
    abstract val titleRes: Int

    data class Characters(@StringRes override val titleRes: Int) : ScreenType()
    data class Locations(@StringRes override val titleRes: Int) : ScreenType()
    data class Episodes(@StringRes override val titleRes: Int) : ScreenType()
}