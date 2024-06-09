package com.example.projectf.presentation.allusers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectf.domain.models.GithubUser
import com.example.projectf.domain.models.Repositories
import com.example.projectf.domain.usecases.AllUsersUseCase
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
import javax.inject.Inject

@HiltViewModel
class AllUsersViewModel @Inject constructor(
    private val getAllUsersUseCase: AllUsersUseCase
):ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun getAllUsers() {
       getAllUsersUseCase().
        flowOn(Dispatchers.IO)
            .onEach { resource ->
                when(resource){
                    is Resource.Error -> Unit
                    Resource.Loading -> Unit
                    is Resource.Success -> _state.update { it.copy(users = resource.model) }
                }

            }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }

    data class State(
        val users: List<GithubUser> = emptyList()
    )
}