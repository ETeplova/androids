package com.example.lab1_android.ui.base

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {
    abstract fun bindViewModel()
    abstract fun observeViewModel()

    fun showError(message: String, appContext: Context) {
        Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
    }
}