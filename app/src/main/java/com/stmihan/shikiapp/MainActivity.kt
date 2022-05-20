package com.stmihan.shikiapp

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.stmihan.nav_utils.NavCommand
import com.stmihan.nav_utils.NavType
import com.stmihan.nav_utils.NavigationProvider

class MainActivity : AppCompatActivity(), NavigationProvider {
    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun launch(navCommand: NavCommand) {
        when (val target = navCommand.target) {
            is NavType.DeepLink -> openDeepLink(
                url = target.url,
                isModal = target.isModal,
                isSingleTop = target.isSingleTop
            )
            is NavType.Browser -> openBrowser(url = target.url)
        }
    }

    private fun openDeepLink(url: Uri, isModal: Boolean, isSingleTop: Boolean) {
        val navOptions = if (isModal) {
            NavOptions.Builder()
//                .setEnterAnim()
//                .setExitAnim()
//                .setPopEnterAnim()
//                .setPopExitAnim()
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(if (isSingleTop) R.id.nav_app else -1, inclusive = isSingleTop)
                .build()
        } else {
            NavOptions.Builder()
//                .setEnterAnim()
//                .setExitAnim()
//                .setPopEnterAnim()
//                .setPopExitAnim()
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(if (isSingleTop) R.id.nav_app else -1, inclusive = isSingleTop)
                .build()
        }

        navController.navigate(url, navOptions)
    }

    private fun openBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        browserIntent.setPackage("com.android.chrome")
        try {
            this.startActivity(browserIntent)
        } catch (ex: ActivityNotFoundException) {
            browserIntent.setPackage(null)
            this.startActivity(browserIntent)
        }
    }
}