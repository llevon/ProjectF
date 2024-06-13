package com.example.projectf.presentation.allusers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectf.databinding.FragmentAllUsersBinding
import com.example.projectf.presentation.allusers.adapters.AllUsersAdapter
import com.example.projectf.presentation.repositorylist.RepositoriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AllUsersFragment:Fragment() {
    private var _binding: FragmentAllUsersBinding? = null
    private val binding: FragmentAllUsersBinding
        get() = _binding?: throw Exception("fragment binding error")

    private val adapter = AllUsersAdapter()
    private val viewModel: AllUsersViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllUsersBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectState()
        viewModel.getAllUsers()
        setupRecyclerView()
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getAllUsers()
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
            adapter.submitList(state.users)

            state.errorMessage?.let { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
            }

        }.launchIn(lifecycleScope)
    }
    private fun setupRecyclerView() = with(binding) {
        rvAllUsers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvAllUsers.adapter = adapter
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}