package ru.steeloscar.gitinfocleanarchitecture.presentation.view.base

import android.content.Intent
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserRepository
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.RepositoryCommitsUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserProfileUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserRepositoryUI

interface FragmentView {

    interface BaseView{
        fun toastInternetConnection()
        fun showProgress()
        fun hideProgress()
        fun showRefresh()
        fun hideRefresh()
        fun showContainerData()
        fun hideContainerData()
    }

    interface Overview: BaseView {
        fun setUserProfile(userProfileUI: UserProfileUI)
        fun startIntent(intent: Intent)
    }

    interface Repositories: BaseView {
        fun setUserRepositories(userRepositories: ArrayList<UserRepositoryUI>)
        fun setCommits(commits: RepositoryCommitsUI)
        fun showEmptyForm()
        fun hideEmptyForm()
    }

    interface Stars: BaseView {
        fun setUserStars(userStars: ArrayList<UserRepository>)
        fun showEmptyForm()
        fun hideEmptyForm()
    }

    interface Follow: BaseView {
        fun setUserProfiles(userProfilesUI: ArrayList<UserProfileUI>)
        fun showEmptyForm()
        fun hideEmptyForm()
    }
}