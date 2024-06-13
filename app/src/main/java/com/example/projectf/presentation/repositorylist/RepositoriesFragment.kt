package com.example.projectf.presentation.repositorylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectf.databinding.FragmentRepositoriesBinding
import com.example.projectf.presentation.main.SharedViewModel
import com.example.projectf.presentation.repositorylist.adapters.RepositoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint

class RepositoriesFragment : Fragment() {
    private var _binding: FragmentRepositoriesBinding? = null
    private val binding get() = _binding!!

    private val adapter = RepositoriesAdapter()
    private val viewModel: RepositoriesViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

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
        setupRecyclerView()
        collectState()
        observeUsernameChanges()
        setupSwipeRefreshLayout()
    }

    private fun observeUsernameChanges() {
        sharedViewModel.username.observe(viewLifecycleOwner) { username ->
            viewModel.getRepositories(username)
        }
    }

    private fun setupSwipeRefreshLayout() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            sharedViewModel.username.value?.let { username ->
                viewModel.getRepositories(username)
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun collectState() {
        viewModel.state.onEach { state ->
            binding.shimmerLayout.apply {
                if (state.isLoading) {
                    startShimmer()
                    visibility = View.VISIBLE
                } else {
                    stopShimmer()
                    visibility = View.GONE
                }
            }
            binding.swipeRefreshLayout.visibility = if (state.isLoading) View.GONE else View.VISIBLE
            adapter.submitList(state.repositories)

            state.errorMessage?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupRecyclerView() {
        binding.rvRepositories.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvRepositories.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
