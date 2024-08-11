package com.network.mapper

import com.common.functional.IMapper
import com.model.OwnerModel
import com.network.model.OwnerResponse

object OwnerMapper : IMapper<OwnerResponse, OwnerModel> {
    override fun map(from: OwnerResponse?): OwnerModel {
        return OwnerModel(
            from?.avatarUrl ?: ""
        )
    }
}