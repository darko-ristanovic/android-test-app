package com.network.mapper

import com.common.functional.IMapper
import com.model.UserDetailsModel
import com.network.model.UserDetailsResponse

internal object UserDetailsMapper : IMapper<UserDetailsResponse, UserDetailsModel> {
    override fun map(from: UserDetailsResponse?): UserDetailsModel {
        return UserDetailsModel(from?.fullName ?: "")
    }
}