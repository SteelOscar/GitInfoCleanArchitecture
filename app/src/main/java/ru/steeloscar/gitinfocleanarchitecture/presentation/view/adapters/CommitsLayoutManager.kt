package ru.steeloscar.gitinfocleanarchitecture.presentation.view.adapters

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class CommitsLayoutManager(context: Context): LinearLayoutManager(context, VERTICAL, false) {

    override fun canScrollVertically(): Boolean {
        return false
    }
}