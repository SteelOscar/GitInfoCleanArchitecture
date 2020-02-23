package ru.steeloscar.gitinfocleanarchitecture.presentation.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import ru.steeloscar.gitinfocleanarchitecture.R
import ru.steeloscar.gitinfocleanarchitecture.commons.AppConstants
import ru.steeloscar.gitinfocleanarchitecture.presentation.presenter.MainPresenterImpl
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters.MainViewPagerAdapter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.ActivityView
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.BaseActivity
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.viewPagerFragments.*

class MainActivity : BaseActivity<MainPresenterImpl>(), ActivityView.Main {

    override var presenter =  MainPresenterImpl()

    override fun getLayout(): Int = R.layout.activity_main

    override fun initializeView() {
        toolbar.navigationIcon =
            ContextCompat.getDrawable(this, R.drawable.ic_person_white_40dp)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
        viewpager.adapter = MainViewPagerAdapter(
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        val fragmentList: ArrayList<Fragment> = arrayListOf(
            OverviewFragment.getInstance(),
            RepositoriesFragment.getInstance(),
            StarsFragment.getInstance(),
            FollowersFragment.getInstance(),
            FollowingFragment.getInstance()
        )
        (viewpager.adapter as MainViewPagerAdapter).addFragments(fragmentList)
        tabLayout.setupWithViewPager(viewpager)
        ic_github.setOnLongClickListener {
            Toast.makeText(this, resources.getString(R.string.git_hub), Toast.LENGTH_SHORT).show()
            true
        }
    }

    override fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun getSharedPreferences(): SharedPreferences =
        getSharedPreferences(AppConstants.APP_PREFERENCES, Context.MODE_PRIVATE)

    override fun startIntent(intent: Intent?) { }

    override fun showProgress() { }

    override fun hideProgress() { }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.sign_out -> {
                presenter.signOut()
                val intent = Intent(this, StartActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
        return true
    }
}