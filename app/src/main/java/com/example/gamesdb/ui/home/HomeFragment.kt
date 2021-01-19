package com.example.gamesdb.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.gamesdb.R
import com.example.gamesdb.data.GameData
import com.example.gamesdb.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),
    GameAdapter.OnItemClickListener {

    private val homeViewModel by viewModels<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        val adapter = GameAdapter(this)
        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = GameLoadStateAdapter { adapter.retry() },
                footer = GameLoadStateAdapter { adapter.retry() }
            )
            buttonRetry.setOnClickListener { adapter.retry() }
        }

        homeViewModel.games.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }


        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                }

            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onItemClick(game: GameData) {
        val action = HomeFragmentDirections.actionNavHomeToDetailsFragment(game)
        findNavController().navigate(action)
    }

}