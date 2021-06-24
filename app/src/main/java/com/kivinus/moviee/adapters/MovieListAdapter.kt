package com.kivinus.moviee.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kivinus.moviee.api.TmdbMovieResponse
import com.kivinus.moviee.databinding.ListItemMovieBinding

class MovieListAdapter :
    PagingDataAdapter<TmdbMovieResponse, MovieViewHolder>(MovieDiffCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            ListItemMovieBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(getItem(position)?.poster_path)


}

class MovieViewHolder(
    private val binding: ListItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(path: String?) {
        Glide.with(binding.root)
            .load("https://image.tmdb.org/t/p/w500/$path")
            .into(binding.moviePoster)
    }
}

class MovieDiffCallBack : DiffUtil.ItemCallback<TmdbMovieResponse>() {
    override fun areItemsTheSame(
        oldItem: TmdbMovieResponse,
        newItem: TmdbMovieResponse
    ): Boolean = oldItem.id == newItem.id


    override fun areContentsTheSame(
        oldItem: TmdbMovieResponse,
        newItem: TmdbMovieResponse
    ): Boolean = oldItem == newItem

}