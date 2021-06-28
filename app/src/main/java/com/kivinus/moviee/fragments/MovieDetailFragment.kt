package com.kivinus.moviee.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kivinus.moviee.R
import com.kivinus.moviee.databinding.FragmentHomeBinding
import com.kivinus.moviee.databinding.FragmentMovieDetailBinding
import com.kivinus.moviee.viewmodels.HomeViewModel
import com.kivinus.moviee.viewmodels.MovieDetailViewModel

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val args: MovieDetailFragmentArgs by navArgs()

    // view binding
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    // view model
    private val viewModel by viewModels<MovieDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedMovieId = args.selectedMovieId
        _binding = FragmentMovieDetailBinding.bind(view)
        binding.textView.text = viewModel.selectedMovieId.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}