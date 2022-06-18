package ru.gorshenev.test.presentation.list_screen.model

sealed class ItemInfo {
    abstract val name: String

    data class Character(
        override val name: String,
        val imageUrl: String,
        val gender: String,
        val species: String,
        val location: String,
    ) : ItemInfo()

    data class Location(
        override val name: String,
        val type: String,
        val dimension: String,
    ) : ItemInfo()

    data class Episode(
        override val name: String,
        val airDate: String,
        val season: String,
        val episode: String,
    ) : ItemInfo()
}