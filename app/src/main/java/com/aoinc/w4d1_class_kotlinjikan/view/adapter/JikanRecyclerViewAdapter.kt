package com.aoinc.w4d1_class_kotlinjikan.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aoinc.w4d1_class_kotlinjikan.R
import com.aoinc.w4d1_class_kotlinjikan.model.JikanResult
import com.aoinc.w4d1_class_kotlinjikan.view.adapter.JikanRecyclerViewAdapter.JikanViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class JikanRecyclerViewAdapter(private var jikanList: List<JikanResult>) : RecyclerView.Adapter<JikanViewHolder>() {

    fun updateJikanList(updatedList: List<JikanResult>) {
        this.jikanList = updatedList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JikanViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.jikan_result_item, parent, false);
        return JikanViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JikanViewHolder, position: Int) {
        // instead of  jikanList.get(position)
        val jikanItem = jikanList[position]

//        holder.titleTextView.text = jikanItem.title
//        holder.synopsisTextView.text = jikanItem.synopsis

        // apply - one of the scope functions of Kotlin
        holder.apply {
            titleTextView.text = jikanItem.title
            synopsisTextView.text = jikanItem.synopsis

            Glide.with(itemView.context)
                    .applyDefaultRequestOptions(RequestOptions().centerCrop())
                    .load(jikanItem.image_url)
//                    .placeholder(R.drawable.ic_purple_haired_anime_girl)
                    .into(posterImageView)
        }
    }

    override fun getItemCount(): Int = jikanList.size

    inner class JikanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterImageView = itemView.findViewById<ImageView>(R.id.result_imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.result_title_textView)
        val synopsisTextView: TextView = itemView.findViewById(R.id.result_synopsis_textView)
    }
}