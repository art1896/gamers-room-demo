package com.example.gamesdb.data

import androidx.paging.PagingSource
import com.example.gamesdb.api.RawgApi
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class GamePagingSource(
    private val rawgApi: RawgApi
) : PagingSource<Int, GameData>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameData> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = rawgApi.getNewAndTrending(
                pageSize = params.loadSize,
                page = position
            )
            val games = response.results
            LoadResult.Page(
                data = games,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (games.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }
}