package com.feature_repos.userrepos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature_repos.usecase.GetUserReposUseCase
import com.feature_repos.userrepos.model.UserReposViewState.Loading
import com.feature_repos.userrepos.model.UserReposViewState
import com.feature_repos.userrepos.model.UserReposListUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserReposViewModel @Inject constructor(
    private val userReposListUseCase: GetUserReposUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UserReposViewState>(Loading)
    val state get() = _state.asStateFlow()

    init {
        getUserRepos()
    }

    fun getUserRepos() {

        _state.update { Loading }

        viewModelScope.launch {
            userReposListUseCase()
                .onError(::handleErrorReceived)
                .onResult(::handleResultReceived)

        }
    }

    private fun handleErrorReceived(error: Exception) {
        _state.update { UserReposViewState.Error(error) }
    }

    private fun handleResultReceived(data: UserReposListUiModel) {
        _state.update { UserReposViewState.Result(data) }
    }
}