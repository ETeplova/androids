package com.example.lab1_android

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1_android.model.Images
import androidx.core.content.ContextCompat.startActivity

import androidx.test.core.app.ApplicationProvider.getApplicationContext

import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.core.content.ContextCompat.startActivity


class Adapter(private val context: Context, private val dataset: List<Images>) :
    RecyclerView.Adapter<Adapter.ItemViewHolder>() {
    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgView: ImageView = view.findViewById(R.id.item_image)
        val textView: TextView = view.findViewById(R.id.item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imgView.setImageResource(item.imageResourceId)


        if (item.imageResourceId == R.drawable.img1) {
            holder.imgView.setBackgroundColor(Color.parseColor("#631708"))
            holder.imgView.setOnClickListener(View.OnClickListener { view ->
                val act2 = Intent(view.context, CaptainActivity::class.java)
                startActivity(view.context, act2, null)

            })
        }

        else if (item.imageResourceId == R.drawable.img2) {
            holder.imgView.setBackgroundColor(Color.parseColor("#253d2a"))
            holder.imgView.setOnClickListener(View.OnClickListener { view ->
                val act2 = Intent(view.context, WidowActivity::class.java)
                startActivity(view.context, act2, null)
            })
        }
        else {
            holder.imgView.setBackgroundColor(Color.parseColor("#072e61"))
            holder.imgView.setOnClickListener(View.OnClickListener { view ->
                val act2 = Intent(view.context, IronActivity::class.java)
                startActivity(view.context, act2, null)
                holder.textView.text = "Yes my friend"
            })
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}