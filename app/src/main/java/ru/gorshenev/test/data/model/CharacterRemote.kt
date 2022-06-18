package ru.gorshenev.test.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterRemote(
    @SerialName("results")
    val results: List<CharacterResult>
)

@Serializable
data class CharacterResult(
    @SerialName("name")
    val name: String,
    @SerialName("image")
    val imageUrl: String,
    @SerialName("gender")
    val gender: String,
    @SerialName("species")
    val species: String,
    @SerialName("location")
    val location: CharacterLocationRemote,
)

@Serializable
data class CharacterLocationRemote(
    @SerialName("name")
    val name: String
)