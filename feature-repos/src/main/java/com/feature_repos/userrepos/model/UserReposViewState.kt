package com.feature_repos.userrepos.model

sealed class UserReposViewState {
    data class Result(val result: UserReposListUiModel) : UserReposViewState()
    data class Error(val error: Throwable) : UserReposViewState()
    data object Loading : UserReposViewState()
}