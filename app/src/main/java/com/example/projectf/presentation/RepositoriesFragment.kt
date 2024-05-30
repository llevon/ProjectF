package com.example.projectf.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projectf.databinding.FragmentAllUsersBinding
import com.example.projectf.databinding.FragmentRepositoriesBinding

class RepositoriesFragment: Fragment() {
    private var _binding: FragmentRepositoriesBinding? = null
    private val binding: FragmentRepositoriesBinding
        get() = _binding?: throw Exception("fragment binding error")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoriesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}