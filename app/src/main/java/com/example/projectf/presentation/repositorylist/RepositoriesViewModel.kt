package com.example.projectf.presentation.repositorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectf.domain.models.Repositories
import com.example.projectf.domain.usecases.GetRepositoriesListUseCase
import com.example.projectf.domain.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val getRepositoriesListUseCase: GetRepositoriesListUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun getRepositories(username: String) {
        getRepositoriesListUseCase(username).
                flowOn(Dispatchers.IO)
            .onEach { resource ->
                when(resource){
                    is Resource.Error -> {
                        _state.update { it.copy(isLoading = false, errorMessage = resource.exception.message) }
                    }
                    Resource.Loading -> _state.update { it.copy(isLoading = true) }
                    is Resource.Success -> _state.update { it.copy(repositories = resource.model, isLoading = false) }
                }

            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }

    data class State(
        val repositories: List<Repositories> = emptyList(),
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )
}