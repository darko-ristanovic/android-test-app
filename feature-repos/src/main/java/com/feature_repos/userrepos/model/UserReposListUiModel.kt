package com.feature_repos.userrepos.model

import com.model.UserRepoDetailsModel

data class UserReposListUiModel(
    val fullName: String,
    val userReposListModel: List<UserRepoDetailsModel>
)