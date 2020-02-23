package ru.steeloscar.gitinfocleanarchitecture.presentation.view.viewPagerFragments

import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_following.*
import ru.steeloscar.gitinfocleanarchitecture.R
import ru.steeloscar.gitinfocleanarchitecture.commons.configure
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserProfileUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.FollowingPresenterImpl
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters.FollowRecyclerAdapter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.BaseFragment
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.FragmentView

class FollowingFragment: BaseFragment<FollowingPresenterImpl>(), FragmentView.Follow {

    private val adapter = FollowRecyclerAdapter.getInstance(this)!!

    override var presenter = FollowingPresenterImpl.getInstance()

    override fun getLayout(): Int = R.layout.fragment_following

    override fun initializeView() {
        if (FollowingPresenterImpl.firstInstance) presenter.loadingData()
        else showContainerData()
        swipeRefreshFollowing.configure()
        swipeRefreshFollowing.setOnRefreshListener {
            presenter.refreshData()
        }
        followingRecyclerView.adapter = adapter
    }

    override fun toastInternetConnection() {
        hideProgress()
        hideRefresh()
        Toast.makeText(context, resources.getString(R.string.no_internet), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        progressFollowing.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressFollowing.visibility = View.GONE
    }

    override fun showRefresh() {
        swipeRefreshFollowing.isRefreshing = true
    }

    override fun hideRefresh() {
        swipeRefreshFollowing.isRefreshing = false
    }

    override fun showContainerData() {
        hideProgress()
        hideRefresh()
        hideEmptyForm()
        followingRecyclerView.visibility = View.VISIBLE
    }

    override fun hideContainerData() {
        followingRecyclerView.visibility = View.GONE
    }

    override fun showEmptyForm() {
        hideProgress()
        hideRefresh()
        hideContainerData()
        emptyFormFollowing.visibility = View.VISIBLE
    }

    override fun hideEmptyForm() {
        emptyFormFollowing.visibility = View.GONE
    }

    override fun setUserProfiles(userProfilesUI: ArrayList<UserProfileUI>) {
        adapter.setUserProfiles(userProfilesUI)
    }

    companion object {
        private var instance: FollowingFragment? = null

        fun getInstance(): FollowingFragment {
            if (instance == null) instance = FollowingFragment()
            return instance as FollowingFragment
        }

        fun clearData() {
            instance = null
        }
    }
}
