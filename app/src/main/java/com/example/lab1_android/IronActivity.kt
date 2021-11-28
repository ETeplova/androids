package com.example.lab1_android

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lab1_android.data.Datasource

class IronActivity : AppCompatActivity() {
    private val iron = "https://e7.pngegg.com/pngimages/604/83/png-clipart-the-iron-man-symbol-logo-ironman-angle-avengers.png"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.iron)

        val findViewById = findViewById<TextView>(R.id.textView)
        val ironImage = findViewById<ImageView>(R.id.sign)

        findViewById.setText(R.string.about3)

        val options = RequestOptions.overrideOf(300, 300)

        Glide.with(this)
            .load(iron)
            .apply(options)
            .circleCrop()
            .into(ironImage)
    }
}