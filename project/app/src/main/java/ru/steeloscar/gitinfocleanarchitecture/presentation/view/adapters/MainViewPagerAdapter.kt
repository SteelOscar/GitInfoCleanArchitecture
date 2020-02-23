package ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MainViewPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {

    private var fragmentList = ArrayList<Fragment>()

    fun addFragments(fragments: ArrayList<Fragment>){
        fragmentList = fragments
        notifyDataSetChanged()
    }

    private val titleFragmentList = arrayOf(
        "Overview",
        "Repositories",
        "Stars",
        "Followers",
        "Following"
    )

    override fun getItem(position: Int): Fragment = fragmentList[position]

    override fun getCount(): Int = fragmentList.size

    override fun getPageTitle(position: Int): CharSequence? = titleFragmentList[position]

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {}
}