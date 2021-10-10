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

        holder.imgView.setImageResource(item.imageResourceId)
        holder.textView.text = context.resources.getString(item.stringResourceId)

        if (item.imageResourceId == R.drawable.img1)
            holder.imgView.setBackgroundColor(Color.parseColor("#631708"))
        else if (item.imageResourceId == R.drawable.img2)
            holder.imgView.setBackgroundColor(Color.parseColor("#253d2a"))
        else
            holder.imgView.setBackgroundColor(Color.parseColor("#072e61"))
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}