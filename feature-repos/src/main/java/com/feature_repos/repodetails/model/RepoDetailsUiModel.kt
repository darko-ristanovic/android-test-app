package com.feature_repos.repodetails.model

import com.model.RepoTagModel

data class RepoDetailsUiModel(
    val fullName: String,
    val avatarUrl: String,
    val repoName: String,
    val numberOfForks: Int,
    val numberOfWatchers: Int,
    val tags: List<RepoTagModel>
)