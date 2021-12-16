package com.example.lab1_android.utils

import android.view.View

interface RecyclerViewClickListener {

    fun onClick(view: View?, position: Int, id: Int)
}