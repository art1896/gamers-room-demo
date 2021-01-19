package com.example.gamesdb.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.gamesdb.data.GameData
import com.example.gamesdb.data.GameRepository

class DetailsViewModel @ViewModelInject constructor(
    private val repository: GameRepository
) : ViewModel() {

    fun getScreenShots(gameId: Int) = liveData {
        emit(repository.getScreenShots(gameId).results)
    }

    fun getGameDevelopers(gameId: Int) = liveData {
        emit(repository.getGameDevelopers(gameId).developers)
    }

    fun getGameStores(gameId: Int, gameData: GameData) = liveData {
        val filteredList: MutableList<GameData.Store> = ArrayList()
        val gameStores = repository.getGameStores(gameId).results
        for (store: GameData.GameStore in gameStores) {
            gameData.stores?.forEach {
                if (store.id == it.store.id) {
                    it.store.url = store.url
                    filteredList.add(it.store)
                }
            }

        }
        emit(filteredList)
    }


}

