package ru.steeloscar.gitinfocleanarchitecture.presentation.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_start.*
import ru.steeloscar.gitinfocleanarchitecture.R
import ru.steeloscar.gitinfocleanarchitecture.commons.AppConstants
import ru.steeloscar.gitinfocleanarchitecture.commons.GitInfoPreferences
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.StartPresenterImpl
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.BaseActivity
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.ActivityView

class StartActivity : BaseActivity<StartPresenterImpl>(), ActivityView.Start {

    override fun getLayout(): Int = R.layout.activity_start

    override var presenter = StartPresenterImpl.getInstance()

    override fun initializeView() {
        setTheme(R.style.StartTheme)
        presenter.checkToken()
        initializeButton()
    }

    private fun initializeButton() {
        loginButton.setOnClickListener {
            presenter.authorizeUser()
        }

        registerUser.setOnClickListener {
            presenter.registerUser()
        }
    }

    override fun startIntent(intent: Intent?) {
        if (intent == null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        else startActivity(intent)
    }

    override fun getSharedPreferences(): SharedPreferences =
        getSharedPreferences(AppConstants.APP_PREFERENCES, Context.MODE_PRIVATE)

    override fun showToast(message: String?) {
        val errorText = when(message){
            "connection" -> resources.getString(R.string.no_internet)
            "login" -> resources.getString(R.string.error_login)
            else -> null
        }
        Toast.makeText(this, errorText, Toast.LENGTH_SHORT).show()
    }

    override fun showActivity() {
        root_container.visibility = View.VISIBLE
    }

    override fun getLogin(): String? = loginEdit.text.toString()

    override fun showProgress() {
        startProgress.visibility = View.VISIBLE
        loginButton.visibility = View.GONE
    }

    override fun hideProgress() {
        startProgress.visibility = View.GONE
        loginButton.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        if (GitInfoPreferences.getLogin() != null) loginEdit.setText(GitInfoPreferences.getLogin())
        presenter.onResume(intent.data)
    }
}
