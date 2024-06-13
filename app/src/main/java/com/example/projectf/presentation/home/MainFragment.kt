package com.example.projectf.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.projectf.R
import com.example.projectf.databinding.FragentMainBinding

class MainFragment : Fragment() {
    private var _binding: FragentMainBinding? = null
    private val binding: FragentMainBinding
        get() = _binding ?: throw Exception("fragment binding error")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNavigation()

    }
    private fun setupBottomNavigation() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        val navHostController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navHostController)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}