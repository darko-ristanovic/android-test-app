package com.feature_repos.repodetails.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.feature_repos.repodetails.model.RepoDetailsUiModel
import com.feature_repos.repodetails.model.RepoDetailsViewState
import com.feature_repos.repodetails.model.RepoDetailsViewState.Loading
import com.feature_repos.usecase.GetRepoDetailsUseCase
import com.feature_repos.userrepos.model.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val getRepoDetailsUseCase: GetRepoDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<RepoDetailsViewState>(Loading)
    val state get() = _state.asStateFlow()

    init { getRepoDetails() }

    fun getRepoDetails() {

        _state.update { Loading }

        val repoName = savedStateHandle.get<String>(Constants.PARAM_REPO_NAME)
        val fullName = savedStateHandle.get<String>(Constants.PARAM_FULL_NAME)

        if (repoName != null && fullName != null) {
            viewModelScope.launch {
                getRepoDetailsUseCase(repoName, fullName)
                    .onError(::handleErrorReceived)
                    .onResult(::handleResultReceived)
            }
        } else _state.update { RepoDetailsViewState.Error(Exception()) }
    }

    private fun handleErrorReceived(error: Exception) {
        _state.update { RepoDetailsViewState.Error(error) }
    }

    private fun handleResultReceived(data: RepoDetailsUiModel) {
        _state.update { RepoDetailsViewState.Result(data) }
    }
}