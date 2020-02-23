package ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters

import kotlinx.android.synthetic.main.follow_recycler_adapter_layout.view.*
import ru.steeloscar.gitinfocleanarchitecture.R
import ru.steeloscar.gitinfocleanarchitecture.commons.*
import ru.steeloscar.gitinfocleanarchitecture.presentation.model.UserProfileUI
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.base.BaseRecyclerViewAdapter
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.viewPagerFragments.FollowersFragment
import ru.steeloscar.gitinfocleanarchitecture.presentation.view.viewPagerFragments.FollowingFragment

class FollowRecyclerAdapter private constructor(): BaseRecyclerViewAdapter() {

    private var userProfiles = ArrayList<UserProfileUI>()
    override fun getLayout(): Int = R.layout.follow_recycler_adapter_layout

    override fun getItemCount(): Int = userProfiles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.binding.userProfile = userProfiles[position]
        with(holder.itemView) {
            userProfiles[position].let {
                followAvatar.setImage(it.avatarUrl)
                userNameFollow.setValue(it.name)
                userLoginFollow.setValue(it.login)
                userEmailFollow.setValue(it.email)
                userBlogFollow.setTextBlog(it.blog)
                userCompanyFollow.setValue(it.company)
                userLocationFollow.setValue(it.location)
                userUpdatedFollow.setDate("Updated", it.updateDate)
                userJoinedFollow.setDate("Joined", it.joinedDate)
                userBioFollow.setValue(it.bio)
            }
        }
    }

    fun setUserProfiles(_userProfiles: ArrayList<UserProfileUI>) {
        userProfiles = _userProfiles
        notifyDataSetChanged()
    }

    companion object {
        private var instanceFollowing: FollowRecyclerAdapter? = null
        private var instanceFollowers: FollowRecyclerAdapter? = null

        fun getInstance(type: Any): FollowRecyclerAdapter? =
            when(type) {
                is FollowersFragment -> {
                    if (instanceFollowers == null) instanceFollowers = FollowRecyclerAdapter()
                    instanceFollowers as FollowRecyclerAdapter
                }
                is FollowingFragment -> {
                    if (instanceFollowing == null) instanceFollowing = FollowRecyclerAdapter()
                    instanceFollowing as FollowRecyclerAdapter
                }
                else -> null
            }

        fun clearData() {
            instanceFollowing = null
            instanceFollowers = null
        }
    }
}