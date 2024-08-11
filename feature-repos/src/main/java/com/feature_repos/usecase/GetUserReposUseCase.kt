package com.feature_repos.usecase

import com.common.functional.Either
import com.data.repository.githubrepos.IGitHubRepository
import com.model.UserDetailsModel
import com.model.UserRepoDetailsModel
import com.feature_repos.userrepos.model.UserReposListUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserReposUseCase @Inject constructor(
    private val gitHubRepository: IGitHubRepository
) {

    suspend operator fun invoke(): Either<Exception, UserReposListUiModel> {

        return withContext(Dispatchers.IO) {

            val userDetailsResponse = async { gitHubRepository.getUserDetails() }
            val reposListResponse = async { gitHubRepository.getReposList() }

            processData(userDetailsResponse.await(), reposListResponse.await())
        }
    }

    private fun processData(
        userDetailsResponse: Either<Exception, UserDetailsModel>,
        userReposResponse: Either<Exception, List<UserRepoDetailsModel>>
    ): Either<Exception, UserReposListUiModel> {

        return userDetailsResponse as? Either.Error ?: userReposResponse as? Either.Error
        ?: Either.Result(
            UserReposListUiModel(
                userDetailsResponse.result().fullName,
                userReposResponse.result()
            )
        )
    }
}