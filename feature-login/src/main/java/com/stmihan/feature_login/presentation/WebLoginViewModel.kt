package com.stmihan.feature_login.presentation

import android.graphics.Bitmap
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
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
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
        saveAuthKeyUseCase.invoke(key)
    }
}