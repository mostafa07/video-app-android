package com.example.android.videoapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.videoapp.R
import com.example.android.videoapp.databinding.FragmentLoginBinding
import com.example.android.videoapp.extension.hideKeyboard

class LoginFragment : Fragment() {

    private var _viewBinding: FragmentLoginBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentLoginBinding.inflate(layoutInflater, container, false)

        viewBinding.lifecycleOwner = viewLifecycleOwner
        viewBinding.viewModel = viewModel

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        setupButtonListeners()
        setupEditorActionListeners()
        setupViewModelObservations()
    }

    private fun setupViewModelObservations() {
        viewModel.hasUserLoggedIn.observe(viewLifecycleOwner) { hasLoggedIn ->
            if (hasLoggedIn) {
                findNavController().navigate(R.id.action_loginFragment_to_videoCaptureFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.please_enter_your_login_credentials,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupButtonListeners() {
        viewBinding.loginButton.setOnClickListener {
            viewModel.login()
            hideKeyboard()
        }
        viewBinding.forgotPasswordButton.setOnClickListener {
            Toast.makeText(requireContext(), "Forgot Password Clicked", Toast.LENGTH_SHORT).show()
        }
        viewBinding.createAccountButton.setOnClickListener {
            Toast.makeText(requireContext(), "Create Account Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupEditorActionListeners() {
        viewBinding.passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    viewBinding.loginButton.performClick()
                    true
                }
                else -> false
            }
        }
    }
}