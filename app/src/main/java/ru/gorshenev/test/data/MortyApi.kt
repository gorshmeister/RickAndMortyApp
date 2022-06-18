package ru.gorshenev.test.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gorshenev.test.data.model.CharacterRemote
import ru.gorshenev.test.data.model.EpisodeRemote
import ru.gorshenev.test.data.model.LocationRemote

interface MortyApi {

    @GET("character")
    fun getCharacters(
        @Query("page") page: Int
    ): Call<CharacterRemote>

    @GET("location")
    fun getLocations(
        @Query("page") page: Int
    ): Call<LocationRemote>

    @GET("episode")
    fun getEpisodes(
        @Query("page") page: Int
    ): Call<EpisodeRemote>

}