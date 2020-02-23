package ru.steeloscar.gitinfocleanarchitecture.presentation.presenter

import android.content.Intent
import android.net.Uri
import ru.steeloscar.gitinfocleanarchitecture.commons.AppConstants
import ru.steeloscar.gitinfocleanarchitecture.commons.GitInfoPreferences
import ru.steeloscar.gitinfocleanarchitecture.data.repository.RepositoryImpl
import ru.steeloscar.gitinfocleanarchitecture.domain.interactor.LoginInteractor
import ru.steeloscar.gitinfocleanarchitecture.domain.presenter.StartPresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.base.BasePresenter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.ActivityView

class StartPresenterImpl: BasePresenter<ActivityView.Start>(), StartPresenter {

    private lateinit var interactor: LoginInteractor

    fun checkToken(){
        with(getView().getSharedPreferences()){
            GitInfoPreferences(this)
            interactor = LoginInteractor(this@StartPresenterImpl, RepositoryImpl.newInstance(this))
        }
        if (GitInfoPreferences.getToken() != null) {
            getView().startIntent(null)
            instance = null
        } else {
            getView().showActivity()
        }
    }

    fun authorizeUser() {
        val login = getView().getLogin()
        if (!login.isNullOrBlank()) {
            getView().showProgress()
            GitInfoPreferences.setLogin(login = login)
            val intent = Intent(Intent.ACTION_VIEW, AppConstants.authorizeUri)
            getView().startIntent(intent)
        } else {
            getView().showToast("login")
        }
    }

    fun registerUser() {
        val intent = Intent(Intent.ACTION_VIEW, AppConstants.registerUri)
        getView().startIntent(intent)
    }

    fun onResume(uri: Uri?){
        if ((uri != null) and uri.toString().startsWith(AppConstants.REDIRECT_URI) and GitInfoPreferences.getToken().isNullOrEmpty()){
            getView().showProgress()
            val authenticateCode = uri?.getQueryParameter("code")
            interactor.execute(authenticateCode.toString())
        } else {
            getView().hideProgress()
        }
    }

    override fun detachView() {
        super.detachView()
        interactor.clear()
    }

    override fun interactorCallback(message: String?) =
        with(getView()){
            if (message == null) startIntent(null)
            else showToast(message)
        }

    companion object {
        private var instance: StartPresenterImpl? = null

        fun getInstance(): StartPresenterImpl {
            if (instance == null) instance = StartPresenterImpl()
            return instance as StartPresenterImpl
        }
    }
}