package com.example.gamesdb.api

import com.example.gamesdb.data.GameData

data class ScreenShotsResponse(
    val results: List<GameData.ScreenShot>
)