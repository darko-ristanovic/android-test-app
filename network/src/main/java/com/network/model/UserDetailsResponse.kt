package com.network.model

import com.google.gson.annotations.SerializedName

data class UserDetailsResponse(
    @SerializedName("name") val fullName: String? = null
)