package com.kivinus.moviee.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kivinus.moviee.R
import com.kivinus.moviee.adapters.MovieListAdapter
import com.kivinus.moviee.databinding.FragmentHomeBinding
import com.kivinus.moviee.model.TmdbMovieResponse
import com.kivinus.moviee.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),
    MovieListAdapter.OnItemClickListener {

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
    private val adapter: MovieListAdapter by lazy { MovieListAdapter(this) }

    private fun initView() {
        binding.recyclerView.adapter = adapter
    }

    private fun collectMovieData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movies.collectLatest { movies -> adapter.submitData(movies) }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        initView()
        collectMovieData()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}