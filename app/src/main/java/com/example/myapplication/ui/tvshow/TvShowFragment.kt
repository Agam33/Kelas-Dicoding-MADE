package com.example.myapplication.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.utils.Resource
import com.example.myapplication.databinding.FragmentTvShowBinding
import com.example.myapplication.ui.detail.DetailTvShowActivity
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding

    private val tvShowViewModel: TvShowViewModel by viewModel()

    private lateinit var tvShowAdapter: TvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowBinding.inflate(layoutInflater,container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null) {
            popularTvShows()
            topRatedTvShows()
            onAirTvShows()
            airingTodayTvShows()
        }
    }

    private fun airingTodayTvShows() {
        tvShowViewModel.airingTodayTvShow.observe(viewLifecycleOwner) { airingTodayTvShow ->
            when (airingTodayTvShow) {
                is Resource.Success -> {
                    binding?.linearProgressBar?.visibility = View.GONE
                    tvShowAdapter = TvAdapter()
                    airingTodayTvShow.data?.let {
                        tvShowAdapter.addList(it)
                        tvShowAdapter.setOnItemClickCallback(object :
                            TvAdapter.OnItemClickCallback {

                            override fun detailTv(id: Int) {
                                startActivity(
                                    Intent(
                                        requireContext(),
                                        DetailTvShowActivity::class.java
                                    ).apply {
                                        putExtra(TV_SHOW_ID, id)
                                    })
                            }
                        })
                        binding?.rvAiringTodayTv?.let { rv ->
                            rv.adapter = tvShowAdapter
                            rv.layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            rv.setHasFixedSize(true)
                        }
                    }
                }
                is Resource.Loading -> binding?.linearProgressBar?.visibility = View.VISIBLE
                is Resource.Error -> {
                    airingTodayTvShow.message?.let {
                        Snackbar.make(
                            this.requireView(),
                            it, Snackbar.LENGTH_LONG
                        )
                    }?.show()
                }
                else -> {}
            }
        }
    }

    private fun onAirTvShows() {
        tvShowViewModel.onAirTvShows.observe(viewLifecycleOwner) { onAirTvShows ->
            when (onAirTvShows) {
                is Resource.Success -> {
                    binding?.linearProgressBar?.visibility = View.GONE
                    tvShowAdapter = TvAdapter()
                    onAirTvShows.data?.let {
                        tvShowAdapter.addList(it)
                        tvShowAdapter.setOnItemClickCallback(object :
                            TvAdapter.OnItemClickCallback {

                            override fun detailTv(id: Int) {
                                startActivity(
                                    Intent(
                                        requireContext(),
                                        DetailTvShowActivity::class.java
                                    ).apply {
                                        putExtra(TV_SHOW_ID, id)
                                    })
                            }
                        })
                        binding?.rvOnAirTv?.let { rv ->
                            rv.adapter = tvShowAdapter
                            rv.layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            rv.setHasFixedSize(true)
                        }
                    }
                }
                is Resource.Loading -> binding?.linearProgressBar?.visibility = View.VISIBLE
                is Resource.Error -> {
                    onAirTvShows.message?.let {
                        Snackbar.make(
                            this.requireView(),
                            it, Snackbar.LENGTH_LONG
                        )
                    }?.show()
                }
                else -> {}
            }
        }
    }

    private fun topRatedTvShows() {
        tvShowViewModel.topRatedTvShows.observe(viewLifecycleOwner) { topRatedTvShows ->
            when (topRatedTvShows) {
                is Resource.Success -> {
                    binding?.linearProgressBar?.visibility = View.GONE
                    tvShowAdapter = TvAdapter()
                    topRatedTvShows.data?.let {
                        tvShowAdapter.addList(it)
                        tvShowAdapter.setOnItemClickCallback(object :
                            TvAdapter.OnItemClickCallback {

                            override fun detailTv(id: Int) {
                                startActivity(
                                    Intent(
                                        requireContext(),
                                        DetailTvShowActivity::class.java
                                    ).apply {
                                        putExtra(TV_SHOW_ID, id)
                                    })
                            }
                        })
                        binding?.rvTopRatedTv?.let { rv ->
                            rv.adapter = tvShowAdapter
                            rv.layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            rv.setHasFixedSize(true)
                        }
                    }
                }
                is Resource.Loading -> binding?.linearProgressBar?.visibility = View.VISIBLE
                is Resource.Error -> {
                    topRatedTvShows.message?.let {
                        Snackbar.make(
                            this.requireView(),
                            it, Snackbar.LENGTH_LONG
                        )
                    }?.show()
                }
                else -> {}
            }
        }
    }

    private fun popularTvShows() {
        tvShowViewModel.popularTvShows.observe(viewLifecycleOwner) { popularTvShows ->
            when (popularTvShows) {
                is Resource.Success -> {
                    binding?.linearProgressBar?.visibility = View.GONE
                    tvShowAdapter = TvAdapter()
                    popularTvShows.data?.let {
                        tvShowAdapter.addList(it)
                        tvShowAdapter.setOnItemClickCallback(object :
                            TvAdapter.OnItemClickCallback {

                            override fun detailTv(id: Int) {
                                startActivity(
                                    Intent(
                                        requireContext(),
                                        DetailTvShowActivity::class.java
                                    ).apply {
                                        putExtra(TV_SHOW_ID, id)
                                    })
                            }
                        })
                        binding?.rvPopularTv?.let { rv ->
                            rv.adapter = tvShowAdapter
                            rv.layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                            rv.setHasFixedSize(true)
                        }
                    }
                }
                is Resource.Loading -> binding?.linearProgressBar?.visibility = View.VISIBLE
                is Resource.Error -> {
                    popularTvShows.message?.let {
                        Snackbar.make(
                            this.requireView(),
                            it, Snackbar.LENGTH_LONG
                        )
                    }?.show()
                }
                else -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object {
        const val TV_SHOW_ID = "tv show id"
    }
}