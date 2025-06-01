package com.example.hyperlocalcommunity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem // For ActionBar back button
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.UUID

class CreatePostActivity : AppCompatActivity() {

    private lateinit var radioGroupPostType: RadioGroup
    private lateinit var editTextPostTitle: TextInputEditText
    private lateinit var editTextPostDescription: TextInputEditText
    private lateinit var spinnerPostCategory: Spinner
    private lateinit var editTextContactInfo: TextInputEditText
    private lateinit var buttonSubmitPost: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        title = getString(R.string.create_post_activity_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable Up button

        radioGroupPostType = findViewById(R.id.radioGroupPostType)
        editTextPostTitle = findViewById(R.id.editTextPostTitle)
        editTextPostDescription = findViewById(R.id.editTextPostDescription)
        spinnerPostCategory = findViewById(R.id.spinnerPostCategory)
        editTextContactInfo = findViewById(R.id.editTextContactInfo)
        buttonSubmitPost = findViewById(R.id.buttonSubmitPost)

        // Setup Spinner for categories
        ArrayAdapter.createFromResource(
            this,
            R.array.skill_share_categories_array, // Defined in strings.xml
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerPostCategory.adapter = adapter
        }

        buttonSubmitPost.setOnClickListener {
            submitPost()
        }
    }

    private fun submitPost() {
        val title = editTextPostTitle.text.toString().trim()
        val description = editTextPostDescription.text.toString().trim()
        // Ensure an item is selected in the spinner, otherwise selectedItem might be null
        val selectedCategory = if (spinnerPostCategory.selectedItemPosition >= 0) {
            spinnerPostCategory.selectedItem.toString()
        } else {
            // Handle case where no item is selected, or provide a default/prompt
            Toast.makeText(this, getString(R.string.error_category_unselected), Toast.LENGTH_SHORT).show()
            return
        }
        val contactInfo = editTextContactInfo.text.toString().trim().takeIf { it.isNotEmpty() }

        if (title.isEmpty()) {
            editTextPostTitle.error = getString(R.string.error_title_required)
            Toast.makeText(this, getString(R.string.error_title_required), Toast.LENGTH_SHORT).show()
            return
        }
        if (description.isEmpty()) {
            editTextPostDescription.error = getString(R.string.error_description_required)
            Toast.makeText(this, getString(R.string.error_description_required), Toast.LENGTH_SHORT).show()
            return
        }

        val selectedPostTypeId = radioGroupPostType.checkedRadioButtonId
        val postType: PostType = when (selectedPostTypeId) {
            R.id.radioButtonOfferSkill -> PostType.OFFER_SKILL
            R.id.radioButtonRequestSkill -> PostType.REQUEST_SKILL
            R.id.radioButtonHobbyConnect -> PostType.HOBBY_CONNECT
            else -> {
                // This case should ideally not be reached if a radio button is checked by default
                // or if validation ensures one is selected.
                Toast.makeText(this, "Please select a post type.", Toast.LENGTH_SHORT).show()
                return
            }
        }

        // Create the SkillSharePost object
        val newPost = SkillSharePost(
            id = UUID.randomUUID().toString(),
            userId = "currentUserPlaceholderID", // Replace with actual user ID when you have auth
            userName = "Current User", // Replace with actual user name
            title = title,
            description = description,
            type = postType,
            category = selectedCategory,
            timestamp = System.currentTimeMillis(),
            contactInfo = contactInfo
        )

        // Prepare intent to return data
        val resultIntent = Intent()
        resultIntent.putExtra("new_skill_share_post", newPost) // "new_skill_share_post" is the key
        setResult(Activity.RESULT_OK, resultIntent)
        Toast.makeText(this, getString(R.string.post_submission_success), Toast.LENGTH_LONG).show()
        finish() // Close this activity and return to the previous one
    }

    // Handle the ActionBar Up/Back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}