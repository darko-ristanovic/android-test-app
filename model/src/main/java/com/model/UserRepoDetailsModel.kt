package com.model

data class UserRepoDetailsModel(
    val repoName: String,
    val openedIssuesCount: Int,
    val forksCount: Int,
    val watchersCount: Int,
    val owner: OwnerModel
)