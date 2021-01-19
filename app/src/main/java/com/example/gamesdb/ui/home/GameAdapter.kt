package com.example.gamesdb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gamesdb.data.GameData
import com.example.gamesdb.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class GameAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<GameData, GameAdapter.GameViewHolder>(GAME_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }

    }

    inner class GameViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(game: GameData) {
            binding.apply {
                Picasso.get().load(game.background_image).fit().centerCrop().into(posterImage)
                gameName.text = game.name
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(game: GameData)
    }


    companion object {
        private val GAME_COMPARATOR = object : DiffUtil.ItemCallback<GameData>() {
            override fun areItemsTheSame(oldItem: GameData, newItem: GameData) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: GameData, newItem: GameData) =
                oldItem == newItem


        }
    }

}