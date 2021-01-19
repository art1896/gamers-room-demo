package com.example.gamesdb.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.gamesdb.data.GameRepository


class HomeViewModel @ViewModelInject constructor(
    repository: GameRepository
) : ViewModel() {

    val games = repository.getNewAndTrending().cachedIn(viewModelScope)


}