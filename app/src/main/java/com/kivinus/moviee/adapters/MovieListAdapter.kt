package com.kivinus.moviee.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kivinus.moviee.model.TmdbMovieResponse
import com.kivinus.moviee.databinding.ListItemMovieBinding

class MovieListAdapter(private val clickListener: OnItemClickListener) :
    PagingDataAdapter<TmdbMovieResponse, MovieListAdapter.MovieViewHolder>
        (MovieDiffCallBack) {

    // ADAPTER
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            ListItemMovieBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(getItem(position)?.poster_path)


    interface OnItemClickListener {
        fun onItemClick(movie: TmdbMovieResponse)
    }


    //HOLDER
    inner class MovieViewHolder(
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
        val MovieDiffCallBack = object : DiffUtil.ItemCallback<TmdbMovieResponse>() {
            override fun areItemsTheSame(
                oldItem: TmdbMovieResponse,
                newItem: TmdbMovieResponse
            ): Boolean = oldItem.id == newItem.id


            override fun areContentsTheSame(
                oldItem: TmdbMovieResponse,
                newItem: TmdbMovieResponse
            ): Boolean = oldItem == newItem

        }
    }
}
