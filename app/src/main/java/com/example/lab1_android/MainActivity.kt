package com.example.lab1_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1_android.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val myDataset = Datasource().loadImages()
        val recyclerView = findViewById<RecyclerView>(R.id.image_cards)

        recyclerView.adapter = Adapter(this, myDataset)
        recyclerView.setHasFixedSize(true)
    }
}