package com.datsenko.movieobserver.presentation.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import com.datsenko.movieobserver.R
import com.datsenko.movieobserver.databinding.FragmentPopularMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : RainbowCakeFragment<PopularMoviesViewState, PopularMoviesViewModel>() {

    private var _binding: FragmentPopularMoviesBinding? = null
    private val binding: FragmentPopularMoviesBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.onViewStarted()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun getViewResource(): Int = R.layout.default_empty_layout

    override fun provideViewModel(): PopularMoviesViewModel = viewModels<PopularMoviesViewModel>().value

    override fun render(viewState: PopularMoviesViewState) {
        when (viewState) {
            PopularMoviesViewState.Initial -> {
            }
            is PopularMoviesViewState.ListReady -> {
            }
        }.exhaustive
    }
}
