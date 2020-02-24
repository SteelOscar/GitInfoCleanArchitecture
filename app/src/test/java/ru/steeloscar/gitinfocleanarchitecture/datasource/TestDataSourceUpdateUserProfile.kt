package ru.steeloscar.gitinfocleanarchitecture.datasource

import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.body.UpdateUserProfileBody
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UpdateUserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UpdateUserProfileUI

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

    fun getUpdateUserProfileUI() =
        UpdateUserProfileUI(
            "name",
            "email",
            "blog",
            "company",
            "location",
            null,
            "bio"
        )
}