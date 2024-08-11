package com.feature_repos.usecase

import com.common.functional.Either
import com.data.repository.githubrepos.GitHubRepository
import com.feature_repos.repodetails.model.RepoDetailsUiModel
import com.model.RepoTagModel
import com.model.UserRepoDetailsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetRepoDetailsUseCase @Inject constructor(
    private val gitHubRepository: GitHubRepository
) {

    suspend operator fun invoke(
        repoName: String,
        fullName: String
    ): Either<Exception, RepoDetailsUiModel> {

        return withContext(Dispatchers.IO) {

            val repoDetailsResponse = async { gitHubRepository.getRepoDetails(repoName) }
            val repoTagsResponse = async { gitHubRepository.getRepoTags(repoName) }

            processData(repoDetailsResponse.await(), repoTagsResponse.await(), fullName)
        }
    }

    private fun processData(
        repoDetailsResponse: Either<Exception, UserRepoDetailsModel>,
        repoTagsResponse: Either<Exception, List<RepoTagModel>>,
        fullName: String
    ): Either<Exception, RepoDetailsUiModel> {

        return repoDetailsResponse as? Either.Error ?: repoTagsResponse as? Either.Error
        ?: Either.Result(
            RepoDetailsUiModel(
                fullName = fullName,
                avatarUrl = repoDetailsResponse.result().owner.avatarUrl,
                repoName = repoDetailsResponse.result().repoName,
                numberOfForks = repoDetailsResponse.result().forksCount,
                numberOfWatchers = repoDetailsResponse.result().watchersCount,
                tags = repoTagsResponse.result()
            )
        )
    }
}
