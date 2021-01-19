package com.example.gamesdb.api

import com.example.gamesdb.data.GameData

data class GameStoresResponse(
        val results: List<GameData.GameStore>
)