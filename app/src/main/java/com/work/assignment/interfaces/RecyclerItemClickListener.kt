package com.work.assignment.interfaces

import android.view.View

interface RecyclerItemClickListener {

    fun onRecyclerItemClick(pos: Int, data: Any, type: String, view: View)
}