package com.network.mapper

import com.common.functional.IMapper
import com.model.OwnerModel
import com.model.UserRepoDetailsModel
import com.network.model.UserRepoDetailsResponse

internal object UserRepoDetailsMapper : IMapper<UserRepoDetailsResponse, UserRepoDetailsModel> {
    override fun map(from: UserRepoDetailsResponse?): UserRepoDetailsModel {
        return UserRepoDetailsModel(
            from?.repoName ?: "",
            from?.openedIssuesCount ?: 0,
            from?.forksCount ?: 0,
            from?.watchersCount ?: 0,
            OwnerMapper.map(from?.owner)
        )
    }
}