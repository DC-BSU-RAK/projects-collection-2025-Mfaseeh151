package com.example.hyperlocalcommunity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SkillShareAdapter(
    private val context: Context,
    private val skillShareList: List<SkillSharePost>,
    private val itemClickListener: OnSkillShareItemClickListener
) : RecyclerView.Adapter<SkillShareAdapter.SkillShareViewHolder>() {

    interface OnSkillShareItemClickListener {
        fun onSkillShareItemClick(post: SkillSharePost)
    }

    inner class SkillShareViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewSkillShareTitle)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewSkillShareDescription)
        val typeTextView: TextView = itemView.findViewById(R.id.textViewSkillShareType)
        val categoryTextView: TextView = itemView.findViewById(R.id.textViewSkillShareCategory)
        val postedByTextView: TextView = itemView.findViewById(R.id.textViewSkillSharePostedBy)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    itemClickListener.onSkillShareItemClick(skillShareList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillShareViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_skillshare, parent, false)
        return SkillShareViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SkillShareViewHolder, position: Int) {
        val currentPost = skillShareList[position]

        holder.titleTextView.text = currentPost.title
        holder.descriptionTextView.text = currentPost.description
        holder.postedByTextView.text = context.getString(R.string.posted_by, currentPost.userName)
        holder.categoryTextView.text = context.getString(R.string.category_label, currentPost.category)

        val typeStringRes = when (currentPost.type) {
            PostType.OFFER_SKILL -> R.string.skillshare_type_offer_skill
            PostType.REQUEST_SKILL -> R.string.skillshare_type_request_skill
            PostType.HOBBY_CONNECT -> R.string.skillshare_type_hobby_connect
        }
        holder.typeTextView.text = context.getString(R.string.type_label, context.getString(typeStringRes))
    }

    override fun getItemCount() = skillShareList.size
}