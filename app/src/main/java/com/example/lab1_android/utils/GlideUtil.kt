package com.example.lab1_android.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GlideUtil {
    companion object {
        fun setImage(imageView: ImageView, imgUrl: String) {
            val imageUrl = StringBuilder()
                .append(imgUrl)
                .toString()

            Glide.with(imageView.context).load(imageUrl)
                .into(imageView)
        }

        fun setImageRound(imageView: ImageView, imgUrl: String) {
            val options = RequestOptions.overrideOf(200, 200)
            val imageUrl = StringBuilder()
                .append(imgUrl)
                .toString()

            Glide.with(imageView.context).load(imageUrl)
                .apply(options)
                .circleCrop()
                .into(imageView)
        }
    }
}