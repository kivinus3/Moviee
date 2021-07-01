package com.kivinus.moviee.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.kivinus.moviee.R
import com.kivinus.moviee.adapters.FavoriteAdapter
import com.kivinus.moviee.databinding.FragmentFavoriteBinding
import com.kivinus.moviee.databinding.FragmentHomeBinding
import com.kivinus.moviee.model.MovieEntity
import com.kivinus.moviee.viewmodels.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FavoriteFragment : Fragment(R.layout.fragment_favorite),
    FavoriteAdapter.OnItemClickListener {

    // click listener for rv items
    override fun onItemClick(movie: MovieEntity) {
        val action = FavoriteFragmentDirections
            .actionFavoriteFragmentToMovieDetailFragment(movie.id)
        findNavController().navigate(action)
    }

    // vm
    private val viewModel by viewModels<FavoriteViewModel>()

    // view binding
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    // rv adapter
    private val adapter: FavoriteAdapter by lazy { FavoriteAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoriteBinding.bind(view)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoriteMovies.collectLatest { adapter.submitList(it) }
            }
        }
        binding.recyclerView.adapter = adapter
    }

}