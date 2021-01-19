package com.example.gamesdb.api

import com.example.gamesdb.data.GameData

data class NewAndTrendingResponse(
    val results: List<GameData>
)