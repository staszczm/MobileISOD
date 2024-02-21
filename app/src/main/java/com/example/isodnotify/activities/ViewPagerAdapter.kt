package com.example.isodnotify.activities

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.isodnotify.R

class ViewPagerAdapter(private var title : List<String>, private val onClickListener: OnClickListener) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    interface OnClickListener {
        fun onClick(view: View, title: String)
    }

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTitle: TextView = itemView.findViewById(R.id.newsTitle)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION) {
                    onClickListener.onClick(itemView, title[position])
                }
            }
        }
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.sample_news_tile, p0, false))
    }

    override fun onBindViewHolder(p0: ViewPagerAdapter.Pager2ViewHolder, p1: Int) {
        p0.itemTitle.text = title[p1]
    }

    override fun getItemCount(): Int {
        return title.size
    }
}