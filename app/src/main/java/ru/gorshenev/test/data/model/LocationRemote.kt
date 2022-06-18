package ru.gorshenev.test.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationRemote(
    @SerialName("results")
    val results: List<LocationResult>
)

@Serializable
data class LocationResult(
    @SerialName("name")
    val name: String,
    @SerialName("type")
    val type: String,
    @SerialName("dimension")
    val dimension: String
)