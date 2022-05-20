package com.stmihan.feature_login.presentation

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.stmihan.core.Constants
import com.stmihan.feature_login.R
import com.stmihan.feature_login.databinding.FragmentWebLoginBinding

class WebLoginFragment(
    private val sharedPreferences: SharedPreferences
) : Fragment() {
    private var _binding: FragmentWebLoginBinding? = null
    private val binding get() = _binding!!

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val createClient = createClient()
        binding.wbLoginView.webViewClient = createClient
        binding.wbLoginView.loadUrl(getString(R.string.login_url))

    }

    private fun createClient(): WebViewClient {
        return object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url != null) {
                    if ("/oauth/authorize/" !in url) return
                    val token = url.split("/").last()
                    saveToken(token)
                }
            }
        }
    }

    private fun saveToken(token: String) {
        sharedPreferences
            .edit()
            .putString(Constants.SP_AUTH_KEY, token)
            .apply()
    }
}