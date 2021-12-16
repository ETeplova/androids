package com.example.lab1_android.ui.main.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1_android.R
import com.example.lab1_android.data.model.Images

class CapAdapter(private val context: Context, private val dataset: List<Images>) :
    RecyclerView.Adapter<CapAdapter.ItemViewHolder>() {
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
<<<<<<< HEAD:app/src/main/java/com/example/lab1_android/CapAdapter.kt


        holder.imgView.setBackgroundColor(Color.parseColor("#631708"))
//        holder.imgView.setOnClickListener(View.OnClickListener { view ->
//            val act2 = Intent(view.context, CaptainActivity::class.java)
//            ContextCompat.startActivity(view.context, act2, null)
//        })
=======
        holder.imgView.setBackgroundColor(Color.parseColor("#631708"))
>>>>>>> 22f38af2eb2ca86700908132b522706c64086edd:app/src/main/java/com/example/lab1_android/ui/main/adapter/CapAdapter.kt
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}