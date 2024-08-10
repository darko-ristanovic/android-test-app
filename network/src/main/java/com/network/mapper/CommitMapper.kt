package com.network.mapper

import com.common.functional.IMapper
import com.model.CommitModel
import com.network.model.CommitResponse

object CommitMapper : IMapper<CommitResponse, CommitModel> {
    override fun map(from: CommitResponse?): CommitModel {
        return CommitModel(
            from?.sha ?: "",
            from?.url ?: ""
        )
    }
}