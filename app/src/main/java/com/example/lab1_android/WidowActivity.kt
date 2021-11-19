package com.example.lab1_android

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class WidowActivity : AppCompatActivity() {
    private val widow = "https://pisco.meaww.com/2a1a1004-a64f-4cf1-b671-60c86a4bd77c.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.widow)

        val findViewById = findViewById<TextView>(R.id.textView)
        val widowImage = findViewById<ImageView>(R.id.sign)

        findViewById.setText(R.string.about2)

        val options = RequestOptions.overrideOf(200, 200)

        Glide.with(this)
            .load(widow)
            .apply(options)
            .circleCrop()
            .into(widowImage)
    }
}