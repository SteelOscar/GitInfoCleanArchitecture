package ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccessToken(
    @SerializedName("access_token")
    @Expose
    val accessToken: String,
    @SerializedName("scope")
    @Expose
    var scope: String,
    @SerializedName("token_type")
    @Expose
    var tokenType: String
)