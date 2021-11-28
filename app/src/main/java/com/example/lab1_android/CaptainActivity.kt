package com.example.lab1_android

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lab1_android.data.Datasource

class CaptainActivity : AppCompatActivity() {
    private val captain = "https://m.media-amazon.com/images/I/71vntClRfjL._AC_SL1500_.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.captain)

        val findViewById = findViewById<TextView>(R.id.textView)
        val captainImage = findViewById<ImageView>(R.id.sign)

        findViewById.setText(R.string.about1)

        val options = RequestOptions.overrideOf(300, 300)

        Glide.with(this)
            .load(captain)
            .apply(options)
            .circleCrop()
            .into(captainImage)
    }
}