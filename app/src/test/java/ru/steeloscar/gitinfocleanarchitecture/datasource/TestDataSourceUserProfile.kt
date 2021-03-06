package ru.steeloscar.gitinfocleanarchitecture.datasource

import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserProfile
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserProfile.UserPlan
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserProfileUI

object TestDataSourceUserProfile {

    fun getUsersProfile(): ArrayList<UserProfile> {
        return arrayListOf(
            UserProfile(
                "login1",
                1,
                "node_id1",
                "avatar_url1",
                "gravatar_id1",
                "url1",
                "hrml_url1",
                "followers_url1",
                "following_url1",
                "gists_url1",
                "starred_url1",
                "subscriptions_url1",
                "organizations_url1",
                "repos_url1",
                "events_url1",
                "received_events_url1",
                "type1",
                false,
                "name1",
                "company1",
                "blog1",
                "location1",
                "email1",
                null,
                "bio1",
                1,
                1,
                1,
                1,
                "created1",
                "updated1",
                1,
                1,
                1,
                1,
                1,
                true,
                UserPlan(
                    "name1",
                    1,
                    1,
                    1
                )
            ),
            UserProfile(
                "login2",
                2,
                "node_id2",
                "avatar_url2",
                "gravatar_id2",
                "url2",
                "hrml_url2",
                "followers_url2",
                "following_url2",
                "gists_url2",
                "starred_url2",
                "subscriptions_url2",
                "organizations_url2",
                "repos_url2",
                "events_url2",
                "received_events_url2",
                "type2",
                false,
                "name2",
                "company2",
                "blog2",
                "location2",
                "email2",
                null,
                "bio2",
                2,
                2,
                2,
                2,
                "created2",
                "updated2",
                2,

                2,
                2,
                2,
                2,
                true,
                UserPlan(
                    "name2",
                    2,
                    2,
                    2
                )
            )
        )
    }

    fun getUsersProfileEntity(): ArrayList<UserProfileEntity> {
        return arrayListOf(
            UserProfileEntity(
                "avatar_url1",
                "name1",
                "login1",
                "email1",
                "blog1",
                "company1",
                "location1",
                "updated1",
                "created1",
                "bio1",
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                true,
                null
            ),
            UserProfileEntity(
                "avatar_url2",
                "name2",
                "login2",
                "email2",
                "blog2",
                "company2",
                "location2",
                "updated2",
                "created2",
                "bio2",
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                true,
                null
            )
        )
    }

    fun getUserProfilesUI(): ArrayList<UserProfileUI> {
        return arrayListOf(
            UserProfileUI(
                "avatar_url1",
                "name1",
                "login1",
                "email1",
                "blog1",
                "company1",
                "location1",
                "updated1",
                "created1",
                "bio1",
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                true,
                null
            ),
            UserProfileUI(
                "avatar_url2",
                "name2",
                "login2",
                "email2",
                "blog2",
                "company2",
                "location2",
                "updated2",
                "created2",
                "bio2",
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                2,
                true,
                null
            )
        )
    }
}