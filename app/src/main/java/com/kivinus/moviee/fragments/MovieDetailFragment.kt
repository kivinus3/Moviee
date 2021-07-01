package com.kivinus.moviee.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.kivinus.moviee.R
import com.kivinus.moviee.databinding.FragmentMovieDetailBinding
import com.kivinus.moviee.model.MovieEntity
import com.kivinus.moviee.viewmodels.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    // safe args
    private val args: MovieDetailFragmentArgs by navArgs()

    // view binding
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    // view model
    private val viewModel by viewModels<MovieDetailViewModel>()


    private fun setupUI(movie: MovieEntity) {
        binding.apply {
            val text = movie.id.toString() + movie.isLiked.toString()
            textViewId.text = text
            textViewTitle.text = movie.title
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view binding
        _binding = FragmentMovieDetailBinding.bind(view)

        // set selectedMovie value in vm
        viewModel.init(args.selectedMovieId)

        // observe selectedMovie and update UI
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.selectedMovie.collectLatest { movie ->
                    if (movie != null) {
                        setupUI(movie)
                    }
                }
            }
        }

        // MovieEntity isLiked true/false
        binding.btnLike.setOnClickListener { viewModel.changeFavoriteStatus() }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}