package com.kivinus.moviee.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.kivinus.moviee.R
import com.kivinus.moviee.adapters.MovieListAdapter
import com.kivinus.moviee.databinding.FragmentHomeBinding
import com.kivinus.moviee.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.annotations.TestOnly


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    // view binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // view model
    private val viewModel by viewModels<HomeViewModel>()

    // rv adapter
    private val adapter: MovieListAdapter by lazy { MovieListAdapter() }

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


    fun logMsg(msg: String) {
        Log.d("HomeFragment", msg)
    }
}