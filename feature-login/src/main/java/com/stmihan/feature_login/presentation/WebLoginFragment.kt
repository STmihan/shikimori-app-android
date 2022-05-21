package com.stmihan.feature_login.presentation

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stmihan.core.DeepLinks
import com.stmihan.feature_login.R
import com.stmihan.feature_login.databinding.FragmentWebLoginBinding
import com.stmihan.nav_utils.NavCommand
import com.stmihan.nav_utils.NavType
import com.stmihan.nav_utils.navigate
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebLoginFragment : Fragment() {
    private var _binding: FragmentWebLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WebLoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val createClient = viewModel.createClient { callback() }
        binding.wbLoginView.webViewClient = createClient
        binding.wbLoginView.loadUrl(getString(R.string.login_url))
        binding.wbLoginView.settings.javaScriptEnabled = true
        binding.wbLoginView.settings.javaScriptCanOpenWindowsAutomatically = true
    }

    private fun callback() {
        navigate(
            NavCommand(
                NavType.DeepLink(
                    url = Uri.parse(DeepLinks.MAIN),
                    isModal = false,
                    isSingleTop = true
                )
            )
        )
    }
}