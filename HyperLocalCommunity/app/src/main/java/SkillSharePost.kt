package com.example.hyperlocalcommunity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize // Import Parcelize

@Parcelize // Add this annotation
data class SkillSharePost(
    val id: String,
    val userId: String, // ID of the user who posted (for now, can be a placeholder)
    val userName: String, // Name of the user (for now, can be a placeholder)
    val title: String,
    val description: String,
    val type: PostType, // Enum: OFFER_SKILL, REQUEST_SKILL, HOBBY_CONNECT
    val category: String,
    val timestamp: Long,
    val contactInfo: String? = null
) : Parcelable // Implement Parcelable

@Parcelize // Make Enum Parcelable as well if it's part of a Parcelable class
enum class PostType : Parcelable {
    OFFER_SKILL,
    REQUEST_SKILL,
    HOBBY_CONNECT
}