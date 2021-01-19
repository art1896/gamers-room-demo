package com.example.gamesdb.api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RawgApi {

    companion object {
        const val BASE_URL = "https://api.rawg.io/api/"
    }

    @GET("games/lists/main")
    suspend fun getNewAndTrending(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("discover") discover: Boolean = true,
        @Query("ordering") ordering: String = "-relevance"
    ): NewAndTrendingResponse

    @GET("games/{id}/screenshots")
    suspend fun getGameScreenShots(
        @Path("id") gameId: Int
    ): ScreenShotsResponse

    @GET("games/{id}/stores")
    suspend fun getGameStores(
        @Path("id") gameId: Int
    ): GameStoresResponse

    @GET("games/{id}")
    suspend fun getGameDevelopers(
        @Path("id") gameId: Int
    ): GameDevelopersResponse

}