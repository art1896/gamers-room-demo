package com.example.gamesdb.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.gamesdb.api.RawgApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(private val rawgApi: RawgApi) {

    suspend fun getScreenShots(gameId: Int) = rawgApi.getGameScreenShots(gameId)

    suspend fun getGameStores(gameId: Int) = rawgApi.getGameStores(gameId)

    suspend fun getGameDevelopers(gameId: Int) = rawgApi.getGameDevelopers(gameId)



    fun getNewAndTrending() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GamePagingSource(rawgApi) }
        ).liveData

}