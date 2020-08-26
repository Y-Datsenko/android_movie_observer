package com.datsenko.movieobserver.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.zsmb.rainbowcake.base.OneShotEvent
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.extensions.exhaustive
import com.datsenko.movieobserver.R
import com.datsenko.movieobserver.databinding.FragmentLoginBinding
import com.datsenko.movieobserver.presentation.events.GoBack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : RainbowCakeFragment<LoginViewState, LoginViewModel>() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    override fun onStart() {
        super.onStart()
        viewModel.onViewStarted()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener { viewModel.onBackButtonClicked() }
        binding.loginFragmentLogin.setOnClickListener { viewModel.onLoginClicked() }

        binding.loginUsername.addTextChangedListener(
            afterTextChanged = { text ->
                text?.trim()?.toString()?.let(viewModel::onUsernameChanged)
            }
        )
        binding.loginPassword.addTextChangedListener(
            afterTextChanged = { text ->
                text?.trim()?.toString()?.let(viewModel::onPasswordChanged)
            }
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun getViewResource(): Int = R.layout.default_empty_layout

    override fun provideViewModel(): LoginViewModel = viewModels<LoginViewModel>().value

    override fun render(viewState: LoginViewState) {
        when (viewState) {
            LoginViewState.Initial -> {
            }
            is LoginViewState.LoginInfo -> {
                binding.loginUsername.setText(viewState.model.username)
                binding.loginUsername.setSelection(viewState.model.username.length)
                binding.loginPassword.setText(viewState.model.password)
                binding.loginPassword.setSelection(viewState.model.password.length)
            }
        }.exhaustive
    }

    override fun onEvent(event: OneShotEvent) {
        when (event) {
            is GoBack -> findNavController().popBackStack()
            else -> super.onEvent(event)
        }
    }
}
