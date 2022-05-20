package com.stmihan.nav_utils

import android.net.Uri

sealed class NavType {
    data class Browser(val url: String) : NavType()
    data class DeepLink(val url: Uri,
                        val isModal: Boolean,
                        val isSingleTop: Boolean = false
    ) : NavType()
}