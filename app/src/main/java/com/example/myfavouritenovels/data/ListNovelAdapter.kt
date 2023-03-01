package com.example.myfavouritenovels.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.myfavouritenovels.R

class ListNovelAdapter(private val listNovel: ArrayList<NovelHeader>) :
    RecyclerView.Adapter<ListNovelAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: (position: Int) -> Unit

    fun setOnItemClickCallback(callback: (position: Int) -> Unit) {
        onItemClickCallback = callback
    }


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCover: ImageView = itemView.findViewById(R.id.img_item_cover)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_item_title)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_novels, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listNovel.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, description, cover) = listNovel[position]
        Glide.with(holder.imgCover)
            .load(cover)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .into(holder.imgCover);
        holder.tvTitle.text = title
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener { onItemClickCallback(holder.adapterPosition) }
    }

}