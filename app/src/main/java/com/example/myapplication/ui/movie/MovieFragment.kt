package com.example.myapplication.ui.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.utils.Resource
import com.example.myapplication.databinding.FragmentMovieBinding
import com.example.myapplication.ui.detail.DetailMovieActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding


    private val movieViewModel: MovieViewModel by viewModel()

    private lateinit var movieAdapter: MovieAdapter
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null) {
            popularMovies()
            topRatedMovies()
            upcomingMovies()
            nowPlayingMovies()
        }
    }

    private fun upcomingMovies() {
        movieViewModel.upComingMovies.observe(viewLifecycleOwner, { upcomingMovies ->
            when(upcomingMovies) {
                is Resource.Success -> {
                    binding?.linearProgressBar?.visibility = View.GONE
                    movieAdapter = MovieAdapter()
                    upcomingMovies.data?.let {
                        movieAdapter.addList(it)
                        movieAdapter.setOnItemClickCallback(object: MovieAdapter.OnItemClickCallback {

                            override fun detailMovie(id: Int) {
                                startActivity(Intent(requireContext(), DetailMovieActivity::class.java).apply {
                                    putExtra(MOVIE_ID, id)
                                })
                            }
                        })
                        binding?.rvUpcomingMovies?.let { rv ->
                            rv.adapter = movieAdapter
                            rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                            rv.setHasFixedSize(true)
                        }
                    }
                }
                is Resource.Loading ->  binding?.linearProgressBar?.visibility = View.VISIBLE
                is Resource.Error-> {
                    upcomingMovies.message?.let {
                        Snackbar.make(this.requireView(),
                            it, Snackbar.LENGTH_LONG)
                    }?.show()
                }
                else -> {}
            }
        })
    }

    private fun nowPlayingMovies() {
        movieViewModel.nowPlayingMovies.observe(viewLifecycleOwner, { nowPlayingMovies ->
            when(nowPlayingMovies) {
                is Resource.Success -> {
                    binding?.linearProgressBar?.visibility = View.GONE
                    movieAdapter = MovieAdapter()
                    nowPlayingMovies.data?.let {
                        movieAdapter.addList(it)
                        movieAdapter.setOnItemClickCallback(object: MovieAdapter.OnItemClickCallback {

                            override fun detailMovie(id: Int) {
                                startActivity(Intent(requireContext(), DetailMovieActivity::class.java).apply {
                                    putExtra(MOVIE_ID, id)
                                })
                            }
                        })
                        binding?.rvNowPlayingMovies?.let { rv ->
                            rv.adapter = movieAdapter
                            rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                            rv.setHasFixedSize(true)
                        }
                    }
                }
                is Resource.Loading -> {
                    binding?.linearProgressBar?.visibility = View.VISIBLE
                }
                is Resource.Error-> { }
                else -> {}
            }
        })
    }

    private fun popularMovies() {
        movieViewModel.popularMovies.observe(viewLifecycleOwner, { popularMovies ->
            when(popularMovies) {

               is Resource.Success -> {
                   popularMovies.data?.let {
                       movieAdapter = MovieAdapter()
                       movieAdapter.addList(it)
                       movieAdapter.setOnItemClickCallback(object: MovieAdapter.OnItemClickCallback {

                           override fun detailMovie(id: Int) {
                               startActivity(Intent(requireContext(), DetailMovieActivity::class.java).apply {
                                   putExtra(MOVIE_ID, id)
                               })
                           }
                       })
                       binding?.rvPopularMovies?.let { rv ->
                           rv.adapter = movieAdapter
                           rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                           rv.setHasFixedSize(true)
                       }
                   }
                   binding?.linearProgressBar?.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding?.linearProgressBar?.visibility = View.VISIBLE
                }
                is Resource.Error-> { }
                else -> {}
            }
        })
    }

    private fun topRatedMovies() {
        movieViewModel.topRatedMovies.observe(viewLifecycleOwner, { topRatedMovies ->
            when(topRatedMovies) {
                is Resource.Success -> {
                    topRatedMovies.data?.let {
                        movieAdapter = MovieAdapter()
                        movieAdapter.addList(it)
                        movieAdapter.setOnItemClickCallback(object: MovieAdapter.OnItemClickCallback {

                            override fun detailMovie(id: Int) {
                                startActivity(Intent(requireContext(), DetailMovieActivity::class.java).apply {
                                    putExtra(MOVIE_ID, id)
                                })
                            }
                        })
                        binding?.rvTopRatedMovies?.let { rv ->
                            rv.adapter = movieAdapter
                            rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                            rv.setHasFixedSize(true)
                        }
                    }
                    binding?.linearProgressBar?.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding?.linearProgressBar?.visibility = View.VISIBLE
                }
                is Resource.Error-> {}
                else -> {}
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    companion object {
        const val MOVIE_ID = "movie id"
    }
}