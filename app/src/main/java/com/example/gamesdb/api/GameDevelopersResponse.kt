package com.example.gamesdb.api

import com.example.gamesdb.data.GameData

data class GameDevelopersResponse(
    val developers: List<GameData.Developer>
)