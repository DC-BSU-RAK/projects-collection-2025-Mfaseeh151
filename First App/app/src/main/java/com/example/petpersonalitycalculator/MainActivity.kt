package com.example.petpersonalitycalculator

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var petTypeSpinner: Spinner
    private lateinit var petBehaviorSpinner: Spinner
    private lateinit var calculateButton: Button

    private val types = arrayOf("Dog", "Cat", "Bird", "Rabbit")
    private val behaviors = arrayOf("Lazy", "Playful", "Curious", "Protective")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        petTypeSpinner = findViewById(R.id.petTypeSpinner)
        petBehaviorSpinner = findViewById(R.id.petBehaviorSpinner)
        calculateButton = findViewById(R.id.calculateButton)

        petTypeSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types)
        petBehaviorSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, behaviors)

        calculateButton.setOnClickListener {
            val pet = petTypeSpinner.selectedItem.toString()
            val behavior = petBehaviorSpinner.selectedItem.toString()
            showModal(pet, behavior)

            val instructionsButton = findViewById<Button>(R.id.instructionsButton)
            instructionsButton.setOnClickListener {
                val intent = Intent(this, InstructionsActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun showModal(pet: String, behavior: String) {
        val dialog = Dialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.modal_personality, null)
        dialog.setContentView(view)

        val description = view.findViewById<TextView>(R.id.modalDescription)
        val closeButton = view.findViewById<Button>(R.id.closeModalButton)

        val result = generatePersonality(pet, behavior)
        description.text = result

        closeButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun generatePersonality(pet: String, behavior: String): String {
        return when (pet to behavior) {
            "Dog" to "Playful" -> "Your dog is a hyper goofball that lives to fetch tennis balls!"
            "Cat" to "Curious" -> "Your cat is a quiet genius plotting world domination."
            "Bird" to "Lazy" -> "Your bird is a chill whistler, happy to vibe in its cage."
            else -> "Your $pet is $behavior — a unique companion with a one-of-a-kind soul!"
        }
    }
}
