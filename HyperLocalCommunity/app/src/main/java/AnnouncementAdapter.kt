package com.example.hyperlocalcommunity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnnouncementAdapter(
    private val announcementList: List<Announcement>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(announcement: Announcement)
    }

    inner class AnnouncementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewItemTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewItemDescription)
        val dateTextView: TextView = itemView.findViewById(R.id.textViewItemDate)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(announcementList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_announcement, parent, false)
        return AnnouncementViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        val currentItem = announcementList[position]
        holder.titleTextView.text = currentItem.title
        holder.descriptionTextView.text = currentItem.description
        holder.dateTextView.text = currentItem.date
    }

    override fun getItemCount() = announcementList.size
}