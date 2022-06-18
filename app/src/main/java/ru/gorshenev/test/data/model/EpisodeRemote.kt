package ru.gorshenev.test.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodeRemote(
    @SerialName("results")
    val results: List<EpisodeResults>,
)

@Serializable
data class EpisodeResults(
    @SerialName("name")
    val name: String,
    @SerialName("air_date")
    val air_date: String,
    @SerialName("episode")
    val episode: String,
)
