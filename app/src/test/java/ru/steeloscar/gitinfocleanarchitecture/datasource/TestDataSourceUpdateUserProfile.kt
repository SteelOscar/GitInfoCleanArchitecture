package ru.steeloscar.gitinfocleanarchitecture.datasource

import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.body.UpdateUserProfileBody
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UpdateUserProfileEntity

object TestDataSourceUpdateUserProfile {

    fun getUpdateUserProfileBody(): UpdateUserProfileBody =
        UpdateUserProfileBody(
            "name",
            "email",
            "blog",
            "company",
            "location",
            null,
            "bio"
        )

    fun getUpdateUserProfileEntity() =
        UpdateUserProfileEntity(
            "name",
            "email",
            "blog",
            "company",
            "location",
            null,
            "bio"
        )
}