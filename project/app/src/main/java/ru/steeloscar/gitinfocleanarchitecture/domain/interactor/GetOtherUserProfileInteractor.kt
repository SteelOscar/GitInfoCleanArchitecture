package ru.steeloscar.gitinfocleanarchitecture.domain.interactor

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.steeloscar.gitinfocleanarchitecture.domain.entity.UserProfileEntity
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.base.BaseInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.FollowPresenter
import ru.steeloscar.gitinfocleanarchitecture.domain.repository.Repository

class GetOtherUserProfileInteractor(private val presenter: FollowPresenter, private val repository: Repository): BaseInteractor() {

    private var count = 0

    fun execute(followProfiles: ArrayList<UserProfileEntity>) {
        followProfiles.map { userProfileEntity ->
            disposables.add(repository.getOtherUserProfile(userProfileEntity.login.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    userProfileEntity.setProfile(it)
                    count++
                    if (count == followProfiles.size) {
                        presenter.showResult(followProfiles)
                        count = 0
                    }
                }
            )
        }
    }
}