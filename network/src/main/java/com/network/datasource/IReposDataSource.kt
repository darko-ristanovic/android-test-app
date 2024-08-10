package com.network.datasource

import com.common.functional.Either
import com.model.RepoTagModel
import com.model.UserDetailsModel
import com.model.UserRepoDetailsModel

interface IReposDataSource {

    suspend fun getUserDetails(): Either<Exception, UserDetailsModel>
    suspend fun getUserRepos(): Either<Exception, List<UserRepoDetailsModel>>
    suspend fun getRepoDetails(repoName: String): Either<Exception, UserRepoDetailsModel>
    suspend fun getRepoTags(repoName: String): Either<Exception, List<RepoTagModel>>
}