package ru.steeloscar.gitinfocleanarchitecture.domain.interactor

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.base.BaseInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.FollowPresenter
import ru.steeloscar.gitinfocleanarchitecture.domain.repository.Repository

class GetFollowingInteractor(private val presenter: FollowPresenter, private val repository: Repository): BaseInteractor() {

    private val getOtherUserInteractor = GetOtherUserProfileInteractor(presenter,repository)

    fun execute() {
        disposables.add(repository.getFollowingUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({    followingProfiles ->
                if (followingProfiles.isNotEmpty()) getOtherUserInteractor.execute(followingProfiles)
                else presenter.showEmpty()
            },{
                presenter.showError()
            })
        )
    }
}