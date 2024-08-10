package com.network.datasource

import com.common.functional.Either
import com.model.RepoTagModel
import com.model.UserDetailsModel
import com.model.UserRepoDetailsModel
import com.network.apiservice.GitHubApiService
import com.network.base.RemoteDataSource
import com.network.mapper.RepoTagsMapper
import com.network.mapper.UserDetailsMapper
import com.network.mapper.UserRepoDetailsMapper
import com.network.mapper.UserReposMapper
import javax.inject.Inject

internal class ReposDataSource @Inject constructor(
    private val gitHubApiService: GitHubApiService
) : RemoteDataSource(), IReposDataSource {

    override suspend fun getUserDetails(): Either<Exception, UserDetailsModel> {
        return getResult(UserDetailsMapper) {
            gitHubApiService.getUserDetails()
        }
    }

    override suspend fun getUserRepos(): Either<Exception, List<UserRepoDetailsModel>> {
        return getResult(UserReposMapper) {
            gitHubApiService.getUserRepos()
        }
    }

    override suspend fun getRepoDetails(repoName: String): Either<Exception, UserRepoDetailsModel> {
        return getResult(UserRepoDetailsMapper) {
            gitHubApiService.getRepoDetails(repoName)
        }
    }

    override suspend fun getRepoTags(repoName: String): Either<Exception, List<RepoTagModel>> {
        return getResult(RepoTagsMapper) {
            gitHubApiService.getRepoTags(repoName)
        }
    }
}