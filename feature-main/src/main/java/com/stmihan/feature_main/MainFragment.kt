package com.stmihan.feature_main

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.stmihan.core.DeepLinks
import com.stmihan.feature_main.databinding.FragmentMainBinding
import com.stmihan.nav_utils.NavCommand
import com.stmihan.nav_utils.NavType
import com.stmihan.nav_utils.navigate

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogout.setOnClickListener {
            navigate(
                NavCommand(
                    NavType.DeepLink(
                        url = Uri.parse(DeepLinks.LOGIN),
                        isModal = false,
                        isSingleTop = true
                    )
                )
            )
        }
    }
}