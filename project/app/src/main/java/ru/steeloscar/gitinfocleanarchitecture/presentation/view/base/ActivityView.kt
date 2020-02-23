package ru.steeloscar.gitinfocleanarchitecture.presentation.view.base

import android.content.Intent
import android.content.SharedPreferences

interface ActivityView {

    interface BaseView {
        fun showToast(message: String?)
        fun startIntent(intent: Intent?)
        fun getSharedPreferences(): SharedPreferences
        fun showProgress()
        fun hideProgress()
    }

    interface Start: BaseView {
        fun showActivity()
        fun getLogin(): String?
    }

    interface Main: BaseView {}

    interface Edit: BaseView {}
}