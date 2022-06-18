package ru.gorshenev.test.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.gorshenev.test.data.model.*
import ru.gorshenev.test.presentation.list_screen.model.ItemInfo
import ru.gorshenev.test.presentation.menu_screen.ScreenType

class MortyRepository {

    private val api: MortyApi = Network.api

    fun getItems(
        screenType: ScreenType,
        page: Int,
        onSuccess: (items: List<ItemInfo>) -> Unit,
        onError: (th: Throwable) -> Unit,
    ) {
        when (screenType) {
            is ScreenType.Characters -> getCharacters(page, onSuccess, onError)
            is ScreenType.Locations -> getLocations(page, onSuccess, onError)
            is ScreenType.Episodes -> getEpisodes(page, onSuccess, onError)
        }

    }

    private fun getCharacters(
        page: Int,
        onSuccess: (items: List<ItemInfo>) -> Unit,
        onError: (th: Throwable) -> Unit,
    ) {
        api.getCharacters(page).enqueue(object : Callback<CharacterRemote> {
            override fun onResponse(call: Call<CharacterRemote>,response: Response<CharacterRemote>) {
                val items = response.body()?.results?.convertCharactersToPresentation()
                items?.let(onSuccess)
            }

            override fun onFailure(call: Call<CharacterRemote>, t: Throwable) {
                onError(t)
            }
        })
    }

    private fun getLocations(
        page: Int,
        onSuccess: (items: List<ItemInfo>) -> Unit,
        onError: (th: Throwable) -> Unit,
    ) {
        api.getLocations(page).enqueue(object : Callback<LocationRemote> {
            override fun onResponse(call: Call<LocationRemote>,response: Response<LocationRemote>) {
                val items = response.body()?.results?.convertLocationsToPresentation()
                items?.let(onSuccess)
            }

            override fun onFailure(call: Call<LocationRemote>, t: Throwable) {
                onError(t)
            }

        })
    }

    private fun getEpisodes(
        page: Int,
        onSuccess: (items: List<ItemInfo>) -> Unit,
        onError: (th: Throwable) -> Unit,
    ) {
        api.getEpisodes(page).enqueue(object : Callback<EpisodeRemote> {
            override fun onResponse(call: Call<EpisodeRemote>, response: Response<EpisodeRemote>) {
                val items = response.body()?.results?.convertEpisodesToPresentation()
                items?.let(onSuccess)
            }

            override fun onFailure(call: Call<EpisodeRemote>, t: Throwable) {
                onError(t)
            }

        })
    }

    private fun List<CharacterResult>.convertCharactersToPresentation(): List<ItemInfo.Character> {
        return this.map { remoteCharacter ->
            ItemInfo.Character(
                imageUrl = remoteCharacter.imageUrl,
                name = remoteCharacter.name,
                gender = remoteCharacter.gender,
                species = remoteCharacter.species,
                location = remoteCharacter.location.name
            )
        }
    }

    private fun List<LocationResult>.convertLocationsToPresentation(): List<ItemInfo.Location> {
        return this.map { remoteLocation ->
            ItemInfo.Location(
                name = remoteLocation.name,
                type = remoteLocation.type,
                dimension = remoteLocation.dimension
            )
        }
    }

    private fun List<EpisodeResults>.convertEpisodesToPresentation(): List<ItemInfo.Episode> {
        return this.map { remoteEpisode ->
            ItemInfo.Episode(
                name = remoteEpisode.name,
                airDate = remoteEpisode.air_date,
                season = remoteEpisode.episode.substring(1, 3),
                episode = remoteEpisode.episode.substring(4)
            )
        }
    }
}
