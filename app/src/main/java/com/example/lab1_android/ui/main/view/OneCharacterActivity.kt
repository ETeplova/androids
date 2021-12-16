package com.example.lab1_android.ui.main.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lab1_android.R
import com.example.lab1_android.data.model.SingleCharacter
import com.example.lab1_android.ui.base.BaseActivity
import com.example.lab1_android.ui.main.viewmodel.OneCharacterViewModel
import com.example.lab1_android.utils.GlideUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OneCharacterActivity : BaseActivity() {
    private var characterId: Int? = 0

    private lateinit var viewModel: OneCharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.one_character)
        characterId = intent.getIntExtra("CHARACTER_ID", 0)

        //Toast.makeText(baseContext, characterId.toString(), Toast.LENGTH_SHORT).show()
        this.bindViewModel()
        viewModel.fetchCharacter(characterId!!)
    }

    override fun bindViewModel() {
        viewModel = ViewModelProvider(this).get(OneCharacterViewModel::class.java)
        this.observeViewModel()
    }

    override fun observeViewModel() {
        viewModel.error.observe(this, Observer<String> {
            showError(it, this)
        })
        viewModel.character.observe(this, Observer {
            initView(it)
        })
    }

    private fun initView(it: SingleCharacter) {
        val full = findViewById<ImageView>(R.id.imageView)
        val descr = findViewById<TextView>(R.id.textView)
        val circle = findViewById<ImageView>(R.id.sign)
        descr.text = it.name

        val imageUrl = it.thumbnail?.path + "." + it.thumbnail?.extension
        GlideUtil.setImageRound(circle, imageUrl)
        GlideUtil.setImage(full, imageUrl)
    }
}