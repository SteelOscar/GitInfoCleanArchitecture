package ru.steeloscar.gitinfocleanarchitecture.presentation.presenter

import ru.steeloscar.gitinfocleanarchitecture.data.repository.RepositoryImpl
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.UserRepository
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.GetStarsInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.StarsPresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.base.BasePresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters.StarsRecyclerAdapter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.FragmentView
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.viewPagerFragments.StarsFragment
import java.util.*

class StarsPresenterImpl private constructor():  BasePresenter<FragmentView.Stars>(), StarsPresenter{

    private val interactor = GetStarsInteractor(this, RepositoryImpl.getInstance())

    fun refreshData() {
        getView().showRefresh()
        getStars()
    }

    fun loadingData(){
        getView().showProgress()
        getStars()
    }

    private fun getStars() {
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

    override fun showResult(arrayListStars: ArrayList<UserRepository>) {
        with(getView()) {
            setUserStars(arrayListStars)
            showContainerData()
        }
    }

    companion object {
        private var instance: StarsPresenterImpl? = null
        var firstInstance = true

        fun getInstance(): StarsPresenterImpl {
            if (instance == null) {
                instance = StarsPresenterImpl()
                return instance as StarsPresenterImpl
            }
            firstInstance = false
            return  instance as StarsPresenterImpl
        }

        fun clearData() {
            instance = null
            firstInstance = true
            StarsFragment.clearData()
            StarsRecyclerAdapter.clearData()
        }
    }
}