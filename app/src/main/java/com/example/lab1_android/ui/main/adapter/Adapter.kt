package com.example.lab1_android.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1_android.R
import com.example.lab1_android.data.model.SingleCharacter
import com.example.lab1_android.utils.GlideUtil
import com.example.lab1_android.utils.RecyclerViewClickListener


class Adapter(private val dataset: List<SingleCharacter>, private val listener: RecyclerViewClickListener) :
    RecyclerView.Adapter<Adapter.ItemViewHolder>() {
    class ItemViewHolder(view: View, listener: RecyclerViewClickListener, dataset: List<SingleCharacter>) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private val dataSet = dataset
        private val mListener = listener
        val imgView: ImageView = view.findViewById(R.id.item_image)
        val textView: TextView = view.findViewById(R.id.item_name)

        init {
            imgView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            mListener.onClick(v, adapterPosition, dataSet[adapterPosition].id!!)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ItemViewHolder(adapterLayout, listener, dataset)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        val id = dataset[position].id
        holder.textView.text = item.name
        val imageUrl = item.thumbnail?.path + "." + item.thumbnail?.extension
        GlideUtil.setImage(holder.imgView, imageUrl)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}