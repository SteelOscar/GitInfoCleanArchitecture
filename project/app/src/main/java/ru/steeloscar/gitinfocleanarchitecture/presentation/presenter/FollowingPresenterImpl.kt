package ru.steeloscar.gitinfocleanarchitecture.presentation.presenter

import ru.steeloscar.gitinfocleanarchitecture.data.repository.RepositoryImpl
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.GetFollowingInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.FollowPresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.mapper.ArrayListUserMapper
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.base.BasePresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.FragmentView
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.viewPagerFragments.FollowingFragment

class FollowingPresenterImpl private constructor(): BasePresenter<FragmentView.Follow>(), FollowPresenter {


    private val interactor = GetFollowingInteractor(this, RepositoryImpl.getInstance())

    fun refreshData() {
        getView().showRefresh()
        getFollowing()
    }

    fun loadingData() {
        getView().showProgress()
        getFollowing()
    }


    private fun getFollowing() {
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
        private var instance: FollowingPresenterImpl? = null
        var firstInstance = true

        fun getInstance(): FollowingPresenterImpl {
            if (instance == null) {
                instance = FollowingPresenterImpl()
                return instance as FollowingPresenterImpl
            }
            firstInstance = false
            return  instance as FollowingPresenterImpl
        }

        fun clearData() {
            instance = null
            firstInstance = true
            FollowingFragment.clearData()
        }
    }
}