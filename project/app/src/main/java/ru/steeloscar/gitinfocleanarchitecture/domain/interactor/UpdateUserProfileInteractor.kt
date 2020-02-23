package ru.steeloscar.gitinfocleanarchitecture.domain.interactor

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UpdateUserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.base.BaseInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.EditProfilePresenter
import ru.steeloscar.gitinfocleanarchitecture.domain.repository.MainRepository

class UpdateUserProfileInteractor(private val presenter: EditProfilePresenter, private val repository: MainRepository): BaseInteractor() {
    fun execute(updateUserProfileEntity: UpdateUserProfileEntity) {
        disposables.add(repository.updateUserProfile(updateUserProfileEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                presenter.showResult(it)
            }, {
                presenter.showError()
            })
        )
    }
}