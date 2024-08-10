package com.network.apiservice

import com.network.base.Endpoint.GET_REPO_DETAILS
import com.network.base.Endpoint.GET_REPO_TAGS
import com.network.base.Endpoint.GET_USER_DETAILS
import com.network.base.Endpoint.GET_USER_REPOS
import com.network.model.RepoTagResponse
import com.network.model.UserDetailsResponse
import com.network.model.UserRepoDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GitHubApiService {

    @GET(GET_USER_DETAILS)
    suspend fun getUserDetails(): Response<UserDetailsResponse>

    @GET(GET_USER_REPOS)
    suspend fun getUserRepos(): Response<List<UserRepoDetailsResponse>>

    @GET(GET_REPO_DETAILS)
    suspend fun getRepoDetails(
        @Path("repo") repoName: String
    ): Response<UserRepoDetailsResponse>

    @GET(GET_REPO_TAGS)
    suspend fun getRepoTags(
        @Path("repo") repoName: String
    ): Response<List<RepoTagResponse>>
}