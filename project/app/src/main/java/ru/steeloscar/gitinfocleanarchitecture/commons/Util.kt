package ru.steeloscar.gitinfocleanarchitecture.commons

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import ru.steeloscar.gitinfocleanarchitecture.R
import ru.steeloscar.gitinfocleanarchitecture.data.repository.api.model.RepositoryCommit

fun SwipeRefreshLayout.configure() {
    setColorSchemeColors(ContextCompat.getColor(context, R.color.gray2))
    setProgressBackgroundColorSchemeResource(R.color.white)
}

fun ImageView.setImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(this.context)
            .load(url)
            .into(this)
    }
}

fun TextView.setTextBlog(blog: String?) {
    if (blog.isNullOrBlank()) {
        visibility = View.GONE
        return
    }
    text = blog
    if (blog.startsWith("https://") or blog.startsWith("http://")) {
        setTextColor(ContextCompat.getColor(context, R.color.blue_web_reference))
        setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(blog))
            ContextCompat.startActivity(context, intent, null)
        }
    }
}

fun TextView.setValue(value: Any?) {
    if(value == null){
        visibility = View.GONE
        return
    }
    text = when(value) {
        is String -> value
        is Int -> value.toString()
        is Boolean -> {
            if (value) "Yes"
            else "No"
        }
        else -> ""
    }
}

fun TextView.setDate(type: String, date: String?) {
    if (date.isNullOrBlank()) {
        visibility = View.GONE
    } else {
        val formatDate = "$type ${date.substring(
            8..9
        )}.${date.substring(
            5..6
        )}.${date.substring(
            0..3
        )}"
        text = formatDate
    }
}

fun View.setVisibility(value: Any?) {
    visibility = if (value == null) View.GONE
    else View.VISIBLE
    if (value is Boolean) {
        visibility = if (value) View.VISIBLE
        else View.GONE
    }
}

fun View.setBoolVisibility(value: Boolean?) {
    visibility = if (value == null) View.GONE
    else View.VISIBLE
}

fun TextView.setStatusRepo(view: View, flags: Array<Boolean?>) {
    text = when {
        flags[0]!! -> resources.getString(R.string.fork_repo)
        flags[1]!! -> resources.getString(R.string.archived_repo)
        flags[2]!! -> resources.getString(R.string.private_repo)
        else -> {
            view.visibility = View.GONE
            return
        }
    }
}

fun TextView.setStatusRepo(flags: Array<Boolean?>) {
    text = when(flags.indexOf(true)) {
        0 -> resources.getString(R.string.fork_repo)
        1 -> resources.getString(R.string.archived_repo)
        2 -> resources.getString(R.string.private_repo)
        else -> return
    }
}

fun View.setStatusVisibility(flags: Array<Boolean?>){
    visibility = if (flags.contains(true)) View.VISIBLE
    else View.GONE
}

fun Button.setButtonStatus(buttonStatus: Boolean) {
    text = if (buttonStatus) resources.getString(R.string.hide_commits)
    else resources.getString(R.string.show_commits)
}

fun TextView.setSize(size: Int?) {
    if (size == null) {
        visibility = View.GONE
        return
    }
    val text = resources.getString(R.string.size) + " $size"
    setText(text)
}

fun ImageView.setCommitAuthorAvatar(url: String?) {
    if (url != null) {
        Glide.with(this.context)
            .load(url)
            .into(this)
    } else {
        Glide.with(this.context)
            .load(R.drawable.ic_commit)
            .into(this)
    }
}

fun TextView.setCommitSha(commitsSha: String) {
    text = commitsSha.substring(0..6)
}

fun TextView.setCommitDate(author: String?, date: String) {
    val text = "$author committed ${date.substring(
        8..9
    )}.${date.substring(
        5..6
    )}.${date.substring(
        0..3
    )}"
    setText(text)
}