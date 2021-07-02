package com.kivinus.moviee.fragments

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.kivinus.moviee.R
import com.kivinus.moviee.adapters.MovieListAdapter
import com.kivinus.moviee.adapters.TmdbLoadStateAdapter
import com.kivinus.moviee.databinding.FragmentHomeBinding
import com.kivinus.moviee.model.TmdbMovieResponse
import com.kivinus.moviee.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),
    MovieListAdapter.OnItemClickListener {

    // click listener for rv items
    override fun onItemClick(movie: TmdbMovieResponse) {
        val action = HomeFragmentDirections
            .actionHomeFragmentToMovieDetailFragment(movie.id)
        findNavController().navigate(action)
    }

    // view binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // view model
    private val viewModel by viewModels<HomeViewModel>()

    // rv adapter
    private val _adapter: MovieListAdapter by lazy { MovieListAdapter(this) }

    private fun setupUI() {

        binding.apply {
            recyclerView.adapter = _adapter.withLoadStateHeaderAndFooter(
                TmdbLoadStateAdapter {_adapter.retry()},
                TmdbLoadStateAdapter {_adapter.retry()}
            )
            recyclerView.itemAnimator = null
            recyclerView.adapter

            btnRetry.setOnClickListener { _adapter.retry() }
        }

        // load state listener
        _adapter.addLoadStateListener { state ->
            binding.apply {
                progressBar.isVisible = state.source.refresh is LoadState.Loading

                recyclerView.isVisible = state.source.refresh is LoadState.NotLoading

                btnRetry.isVisible = state.source.refresh is LoadState.Error
                textViewError.isVisible = state.source.refresh is LoadState.Error

            }
        }
    }

    private fun collectMovieData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movies.collectLatest { movies -> _adapter.submitData(movies) }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        setupUI()
        collectMovieData()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}