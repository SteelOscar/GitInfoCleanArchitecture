package ru.steeloscar.gitinfocleanarchitecture.presentation.view.viewPagerFragments

import android.content.Intent
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_overview.*
import ru.steeloscar.gitinfocleanarchitecture.R
import ru.steeloscar.gitinfocleanarchitecture.commons.*
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserProfileUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.OverviewPresenterImpl
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.BaseFragment
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.FragmentView

class OverviewFragment: BaseFragment<OverviewPresenterImpl>(), FragmentView.Overview {

    override var presenter = OverviewPresenterImpl.newInstance()

    override fun getLayout(): Int = R.layout.fragment_overview

    override fun initializeView() {
        if (UserProfileUI.userProfileUI != null) {
            setUserProfile(UserProfileUI.userProfileUI!!)
            showContainerData()
        } else {
            presenter.loadingData()
        }
        swipeRefreshOverview.configure()
        swipeRefreshOverview.setOnRefreshListener {
            presenter.refreshProfile()
        }
    }

    override fun toastInternetConnection() {
        Toast.makeText(context, resources.getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        progressOverview.visibility = View.VISIBLE
    }

    override fun showRefresh() {
        swipeRefreshOverview.isRefreshing = true
    }

    override fun showContainerData() {
        hideProgress()
        hideRefresh()
        overviewDataContainer.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressOverview.visibility = View.GONE
    }

    override fun hideRefresh() {
        swipeRefreshOverview.isRefreshing = false
    }

    override fun hideContainerData() {
        overviewDataContainer.visibility = View.GONE
    }

    override fun setUserProfile(userProfileUI: UserProfileUI) {
        with(userProfileUI) {
            userAvatarOverview.setImage(avatarUrl)
            userNameOverview.setValue(name)
            userLoginOverview.setValue(login)
            userEmailOverview.setValue(email)
            userBlogOverview.setTextBlog(blog)
            userCompanyOverview.setValue(company)
            userLocationOverview.setValue(location)
            userUpdateOverview.setDate("Updated", updateDate)
            userJoinedOverview.setDate("Joined", joinedDate)
            userBioOverview.setValue(bio)
            headerFollowersOverview.setVisibility(followers)
            headerFollowingOverview.setVisibility(following)
            headerPubGistOverview.setVisibility(pubGists)
            headerPrivateGistOverview.setVisibility(privateGists)
            headerPubReposOverview.setVisibility(pubRepos)
            headerOwnPrivateReposOverview.setVisibility(ownPrivateRepos)
            headerTotPrivateReposOverview.setVisibility(totalPrivateRepos)
            headerDiskOverview.setVisibility(diskUsage)
            headerAuthOverview.setBoolVisibility(twoFactorAuth)
            userFollowersOverview.setValue(followers)
            userFollowingOverview.setValue(following)
            userPubGistOverview.setValue(pubGists)
            userPrivateGistOverview.setValue(privateGists)
            userPubReposOverview.setValue(pubRepos)
            userOwnPrivateReposOverview.setValue(ownPrivateRepos)
            userTotPrivateReposOverview.setValue(totalPrivateRepos)
            userDiskOverview.setValue(diskUsage)
            userAuthOverview.setValue(twoFactorAuth)
        }
    }

    override fun startIntent(intent: Intent) {
        startActivity(intent)
    }

    companion object {
        private var instance: OverviewFragment? = null

        fun getInstance(): OverviewFragment {
            if (instance == null) instance = OverviewFragment()
            return instance as OverviewFragment
        }

        fun clearData() {
            instance = null
        }
    }
}