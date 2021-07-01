package com.kivinus.moviee.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kivinus.moviee.adapters.FavoriteAdapter.MovieHolder
import com.kivinus.moviee.databinding.ListItemMovieBinding
import com.kivinus.moviee.model.MovieEntity

class FavoriteAdapter(private val clickListener: OnItemClickListener) :
    ListAdapter<MovieEntity, MovieHolder>(FavoriteMovieDiffCallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder =
        MovieHolder(
            ListItemMovieBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(getItem(position).posterUrl)
    }

    interface OnItemClickListener {
        fun onItemClick(movie: MovieEntity)
    }


    inner class MovieHolder(
        private val binding: ListItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    val item = getItem(bindingAdapterPosition)
                    if (item != null)
                        clickListener.onItemClick(item)
                }
            }
        }


        fun bind(path: String?) {
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500/$path")
                .into(binding.moviePoster)
        }
    }

    // DIFF_UTIL
    companion object {
        val FavoriteMovieDiffCallBack = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: MovieEntity,
                newItem: MovieEntity
            ): Boolean = oldItem == newItem
        }
    }
}