package ru.steeloscar.gitinfocleanarchitecture.presentation.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_edit_profile.*
import ru.steeloscar.gitinfocleanarchitecture.R
import ru.steeloscar.gitinfocleanarchitecture.commons.AppConstants
import ru.steeloscar.gitinfocleanarchitecture.commons.configure
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UpdateUserProfileUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserProfileUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.EditProfilePresenterImpl
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.ActivityView
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.BaseActivity

class EditProfileActivity : BaseActivity<EditProfilePresenterImpl>(), ActivityView.Edit {

    override var presenter = EditProfilePresenterImpl()

    override fun getLayout(): Int = R.layout.activity_edit_profile

    override fun initializeView() {
        setUserProfile(UserProfileUI.getUpdateUserProfile())
        swipeRefreshEdit.isEnabled = false
        editProfileToolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_30dp)
        setSupportActionBar(editProfileToolbar)
        editProfileToolbar.setNavigationOnClickListener {
            startIntent(null)
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
        swipeRefreshEdit.configure()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.save_edit -> {
                val updateUserProfileBody = UserProfileUI.getUpdateUserProfile()
                presenter.editProfile(UpdateUserProfileUI(
                    userNameEdit.text.toString(),
                    updateUserProfileBody.email,
                    userBlogEdit.text.toString(),
                    userCompanyEdit.text.toString(),
                    userLocationEdit.text.toString(),
                    updateUserProfileBody.hireable,
                    userBioEdit.text.toString()
                ))
            }
        }
        return true
    }

    override fun startIntent(intent: Intent?) {
        finish()
    }

    override fun getSharedPreferences(): SharedPreferences =
        getSharedPreferences(AppConstants.APP_PREFERENCES, Context.MODE_PRIVATE)

    private fun setUserProfile(updateUserProfileUI: UpdateUserProfileUI) {
        with(updateUserProfileUI) {
            userNameEdit.setText(name)
            userBlogEdit.setText(blog)
            userCompanyEdit.setText(company)
            userLocationEdit.setText(location)
            userBioEdit.setText(bio)
        }
    }

    override fun showToast(message: String?) {
        Toast.makeText(this, resources.getString(R.string.failed_update), Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        swipeRefreshEdit.isRefreshing = true
    }

    override fun hideProgress() {

        swipeRefreshEdit.isRefreshing = false
    }
}
