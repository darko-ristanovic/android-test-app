package com.network.mapper

import com.common.functional.IMapper
import com.model.RepoTagModel
import com.network.model.RepoTagResponse

object RepoTagsMapper : IMapper<List<RepoTagResponse>, List<RepoTagModel>> {
    override fun map(from: List<RepoTagResponse>?): List<RepoTagModel> {
        return from?.map { RepoTagMapper.map(it) } ?: emptyList()
    }
}