package com.feature_repos.repodetails.model

sealed class RepoDetailsViewState {

    data class Result(val result: RepoDetailsUiModel) : RepoDetailsViewState()
    data class Error(val error: Throwable) : RepoDetailsViewState()
    data object Loading : RepoDetailsViewState()
}