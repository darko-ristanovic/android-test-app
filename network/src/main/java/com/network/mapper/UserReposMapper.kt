package com.network.mapper

import com.common.functional.IMapper
import com.model.UserRepoDetailsModel
import com.network.model.UserRepoDetailsResponse

object UserReposMapper : IMapper<List<UserRepoDetailsResponse>, List<UserRepoDetailsModel>> {
    override fun map(from: List<UserRepoDetailsResponse>?): List<UserRepoDetailsModel> {
        return from?.map { UserRepoDetailsMapper.map(it) } ?: emptyList()
    }
}