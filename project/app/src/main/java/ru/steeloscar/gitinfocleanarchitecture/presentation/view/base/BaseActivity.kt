package ru.steeloscar.gitinfocleanarchitecture.presentation.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.base.BasePresenter

abstract class BaseActivity<P: BasePresenter<Any?>>: AppCompatActivity() {

    abstract var presenter: P

    abstract fun getLayout(): Int
    abstract fun initializeView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        presenter.attachView(this)
        initializeView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}