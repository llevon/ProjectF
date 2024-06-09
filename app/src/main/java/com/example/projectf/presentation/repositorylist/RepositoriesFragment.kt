package com.example.projectf.presentation.repositorylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectf.databinding.FragmentAllUsersBinding
import com.example.projectf.databinding.FragmentRepositoriesBinding
import com.example.projectf.presentation.repositorylist.adapters.RepositoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class RepositoriesFragment : Fragment() {
    private var _binding: FragmentRepositoriesBinding? = null
    private val binding: FragmentRepositoriesBinding
        get() = _binding ?: throw Exception("fragment binding error")
    private val adapter = RepositoriesAdapter()
    private val viewModel: RepositoriesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectState()
        setupRecyclerView()
        viewModel.getRepositories()
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getRepositories()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun collectState() {
        viewModel.state.onEach {
            adapter.submitList(it.repositories)
        }.launchIn(lifecycleScope)
    }

    private fun setupRecyclerView() = with(binding) {
        rvRepositories.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvRepositories.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}