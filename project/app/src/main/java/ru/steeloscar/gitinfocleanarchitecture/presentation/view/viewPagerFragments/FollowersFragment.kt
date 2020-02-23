package ru.steeloscar.gitinfocleanarchitecture.presentation.view.viewPagerFragments

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_followers.*
import ru.steeloscar.gitinfocleanarchitecture.R
import ru.steeloscar.gitinfocleanarchitecture.commons.configure
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserProfileUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters.FollowRecyclerAdapter
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.FollowersPresenterImpl
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.BaseFragment
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.FragmentView

class FollowersFragment: BaseFragment<FollowersPresenterImpl>(), FragmentView.Follow {

    private val adapter = FollowRecyclerAdapter.getInstance(this)!!

    override var presenter= FollowersPresenterImpl.getInstance()

    override fun getLayout(): Int = R.layout.fragment_followers

    override fun initializeView() {
        if (FollowersPresenterImpl.firstInstance) presenter.loadingData()
        else showContainerData()
        swipeRefreshFollowers.configure()
        swipeRefreshFollowers.setOnRefreshListener {
            presenter.refreshData()
        }
        followersRecyclerView.adapter = adapter
    }

    override fun toastInternetConnection() {
        hideProgress()
        hideRefresh()
        Toast.makeText(context, resources.getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        progressFollowers.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressFollowers.visibility = View.GONE
    }

    override fun showRefresh() {
        swipeRefreshFollowers.isRefreshing = true
    }

    override fun hideRefresh() {
        swipeRefreshFollowers.isRefreshing = false
    }

    override fun showContainerData() {
        hideProgress()
        hideRefresh()
        hideEmptyForm()
        followersRecyclerView.visibility = View.VISIBLE
    }
    override fun hideContainerData() {
        followersRecyclerView.visibility = View.GONE
    }

    override fun showEmptyForm() {
        hideProgress()
        hideRefresh()
        hideContainerData()
        emptyFormFollowers.visibility = View.VISIBLE
    }

    override fun hideEmptyForm() {
        emptyFormFollowers.visibility = View.GONE
    }

    override fun setUserProfiles(userProfilesUI: ArrayList<UserProfileUI>) {
        adapter.setUserProfiles(userProfilesUI)
    }

    companion object {
        private var instance: FollowersFragment? = null

        fun getInstance(): FollowersFragment {
            if (instance == null) instance = FollowersFragment()
            return instance as FollowersFragment
        }

        fun clearData() {
            instance = null
        }
    }
}