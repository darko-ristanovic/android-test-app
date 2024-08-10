package com.data.repository.githubrepos

import com.common.functional.Either
import com.model.RepoTagModel
import com.model.UserDetailsModel
import com.model.UserRepoDetailsModel
import com.network.datasource.IReposDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GitHubRepository @Inject constructor(
    private val reposDataSource: IReposDataSource
) : IGitHubRepository {

    override suspend fun getUserDetails(): Either<Exception, UserDetailsModel> {
        return withContext(Dispatchers.IO) {
            reposDataSource.getUserDetails()
        }
    }

    override suspend fun getReposList(): Either<Exception, List<UserRepoDetailsModel>> {
        return withContext(Dispatchers.IO) {
            reposDataSource.getUserRepos()
        }
    }

    override suspend fun getRepoDetails(repoName: String): Either<Exception, UserRepoDetailsModel> {
        return withContext(Dispatchers.IO) {
            reposDataSource.getRepoDetails(repoName)
        }
    }

    override suspend fun getRepoTags(repoName: String): Either<Exception, List<RepoTagModel>> {
        return withContext(Dispatchers.IO) {
            reposDataSource.getRepoTags(repoName)
        }
    }
}