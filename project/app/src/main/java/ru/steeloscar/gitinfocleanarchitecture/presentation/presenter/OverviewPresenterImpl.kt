package ru.steeloscar.gitinfocleanarchitecture.presentation.presenter

import ru.steeloscar.gitinfocleanarchitecture.data.repository.RepositoryImpl
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.GetUserProfileInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.OverviewPresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.mapper.UserProfileUIMapper
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserProfileUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.base.BasePresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.FragmentView
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.viewPagerFragments.OverviewFragment

class OverviewPresenterImpl private constructor(): BasePresenter<FragmentView.Overview>(), OverviewPresenter {

    private val interactor = GetUserProfileInteractor(this, RepositoryImpl.getInstance())

    fun loadingData() {
        getView().showProgress()
        getUserProfile()
    }

    fun refreshProfile() {
        getView().showRefresh()
        getUserProfile()
    }

    private fun getUserProfile() {
        interactor.execute()
    }

    override fun showResult(userProfileEntity: UserProfileEntity) {
        setUserProfile(UserProfileUIMapper.map(userProfileEntity))
        getView().showContainerData()
    }

    override fun showError() {
        with(getView()) {
            toastInternetConnection()
            hideProgress()
            hideRefresh()
        }
    }

    fun setUserProfile(userProfileUI: UserProfileUI) {
        UserProfileUI.userProfileUI = userProfileUI
        getView().setUserProfile(userProfileUI)
    }

    override fun detachView() {
        super.detachView()
        interactor.clear()
    }

    companion object {
        private var instance: OverviewPresenterImpl? = null

        fun newInstance(): OverviewPresenterImpl {
            if (instance == null) instance = OverviewPresenterImpl()
            return  instance as OverviewPresenterImpl
        }

        fun getInstance(): OverviewPresenterImpl = instance as OverviewPresenterImpl

        fun clearData() {
            instance = null
            UserProfileUI.userProfileUI = null
            OverviewFragment.clearData()
        }
    }
}