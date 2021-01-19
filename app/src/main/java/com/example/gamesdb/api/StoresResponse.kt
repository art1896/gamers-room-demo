package com.example.gamesdb.api

import com.example.gamesdb.data.GameData

data class StoresResponse(
    val results: List<GameData.Stores>
)