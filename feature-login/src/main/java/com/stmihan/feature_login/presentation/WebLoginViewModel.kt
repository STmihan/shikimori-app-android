package com.stmihan.feature_login.presentation

import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModel
import com.stmihan.feature_login.domain.SaveAuthKeyUseCase

class WebLoginViewModel(
    private val saveAuthKeyUseCase: SaveAuthKeyUseCase
) : ViewModel() {

    fun createClient(callback: () -> Unit): WebViewClient {
        return object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (url != null) {
                    if ("/oauth/authorize/" !in url) return
                    val token = url.split("/").last()
                    saveKey(token)
                    callback()
                }
            }
        }
    }

    private fun saveKey(key: String) {
        Log.d("KEY", key)
        saveAuthKeyUseCase.invoke(key)
    }
}