package ru.steeloscar.gitinfocleanarchitecture.presentation.presenter

import ru.steeloscar.gitinfocleanarchitecture.data.repository.MainRepositoryImpl
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.GetFollowersInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.FollowPresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.mapper.ArrayListUserMapper
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.base.BasePresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters.FollowRecyclerAdapter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.FragmentView
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.viewPagerFragments.FollowersFragment

class FollowersPresenterImpl private constructor(): BasePresenter<FragmentView.Follow>(), FollowPresenter {

    private val interactor = GetFollowersInteractor(this, MainRepositoryImpl.getInstance())

    fun refreshData() {
        getView().showRefresh()
        getFollowers()
    }

    fun loadingData() {
        getView().showProgress()
        getFollowers()
    }

    private fun getFollowers() {
        interactor.execute()
    }

    override fun detachView() {
        super.detachView()
        interactor.clear()
    }

    override fun showError() {
        getView().toastInternetConnection()
    }

    override fun showEmpty() {
        getView().showEmptyForm()
    }

    override fun showResult(followProfiles: ArrayList<UserProfileEntity>) {
        with(getView()) {
            setUserProfiles(ArrayListUserMapper.map(followProfiles))
            showContainerData()
        }
    }

    companion object {
        private var instance: FollowersPresenterImpl? = null
        var firstInstance = true

        fun getInstance(): FollowersPresenterImpl {
            if (instance == null) {
                instance = FollowersPresenterImpl()
                return instance as FollowersPresenterImpl
            }
            firstInstance = false
            return  instance as FollowersPresenterImpl
        }

        fun clearData() {
            instance = null
            firstInstance = true
            FollowersFragment.clearData()
            FollowRecyclerAdapter.clearData()
        }
    }
}