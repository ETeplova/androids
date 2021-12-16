package com.example.lab1_android.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1_android.R
import com.example.lab1_android.data.model.SingleCharacter
import com.example.lab1_android.ui.base.BaseActivity
import com.example.lab1_android.ui.main.adapter.Adapter
import com.example.lab1_android.ui.main.viewmodel.MainViewModel
import com.example.lab1_android.utils.RecyclerViewClickListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), RecyclerViewClickListener {
    private var recyclerView: RecyclerView? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.image_cards)

        this.bindViewModel()
    }
    override fun bindViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        this.observeViewModel()
    }

    override fun observeViewModel() {
        viewModel.error.observe(this, Observer<String> {
            showError(it, this)
        })
        viewModel.characters.observe(this, Observer {
            initAdapter(it)
        })
    }

    private fun initAdapter(list: List<SingleCharacter>) {
        recyclerView?.adapter = Adapter(list, this)
        recyclerView?.setHasFixedSize(true)
    }

    override fun onClick(view: View?, position: Int, id: Int) {
        val intent = Intent(this, OneCharacterActivity::class.java)
        intent.putExtra("CHARACTER_ID", id)
        startActivity(intent)
    }
}
