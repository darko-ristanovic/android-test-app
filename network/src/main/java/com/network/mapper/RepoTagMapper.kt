package com.network.mapper

import com.common.functional.IMapper
import com.model.RepoTagModel
import com.network.model.RepoTagResponse

object RepoTagMapper : IMapper<RepoTagResponse, RepoTagModel> {
    override fun map(from: RepoTagResponse?): RepoTagModel {
        return RepoTagModel(
            commit = CommitMapper.map(from?.commit),
            name = from?.name ?: ""
        )
    }
}