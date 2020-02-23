package ru.steeloscar.gitinfocleanarchitecture.presentation.presenter

import ru.steeloscar.gitinfocleanarchitecture.data.repository.MainRepositoryImpl
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.UpdateUserProfileInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.EditProfilePresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.mapper.UpdateUserProfileUIMapper
import ru.steeloscar.gitinfocleanarchitecture.presentation.mapper.UserProfileUIMapper
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UpdateUserProfileUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.base.BasePresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.ActivityView

class EditProfilePresenterImpl: BasePresenter<ActivityView.Edit>(), EditProfilePresenter {

    private val interactor = UpdateUserProfileInteractor(this, MainRepositoryImpl.getInstance())

    fun editProfile(updateUserProfileUI: UpdateUserProfileUI){
        getView().showProgress()
        interactor.execute(UpdateUserProfileUIMapper.forwardMap(updateUserProfileUI))
    }

    override fun detachView() {
        super.detachView()
        interactor.clear()
    }

    override fun showError() {
        getView().showToast(null)
        getView().hideProgress()
    }

    override fun showResult(userProfileEntity: UserProfileEntity) {
        OverviewPresenterImpl.newInstance().setUserProfile(UserProfileUIMapper.map(userProfileEntity))
        getView().startIntent(null)
        getView().hideProgress()
    }
}