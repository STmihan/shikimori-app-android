package com.stmihan.shikiapp.presentation

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stmihan.core.DeepLinks
import com.stmihan.nav_utils.NavCommand
import com.stmihan.nav_utils.NavType
import com.stmihan.nav_utils.navigate
import com.stmihan.shikiapp.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loggedIn.observe(viewLifecycleOwner) {
            if (it) navigate(
                NavCommand(
                    NavType.DeepLink(
                        url = Uri.parse(DeepLinks.MAIN),
                        isModal = false,
                        isSingleTop = true
                    )
                )
            ) else navigate(
                NavCommand(
                    NavType.DeepLink(
                        url = Uri.parse(DeepLinks.LOGIN),
                        isModal = false,
                        isSingleTop = true
                    )
                )
            )
        }

        viewModel.tryAuth()
    }
}