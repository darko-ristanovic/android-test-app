package com.network.model

import com.google.gson.annotations.SerializedName

data class UserRepoDetailsResponse(
    @SerializedName("name") val repoName: String? = null,
    @SerializedName("open_issues") val openedIssuesCount: Int? = null,
    @SerializedName("forks_count") val forksCount: Int? = null,
    @SerializedName("watchers_count") val watchersCount: Int? = null,
    @SerializedName("owner") val owner: OwnerResponse? = null
)