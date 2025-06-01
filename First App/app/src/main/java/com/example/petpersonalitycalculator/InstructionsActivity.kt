package com.example.petpersonalitycalculator

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InstructionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()

            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_instructions)

            val closeBtn = findViewById<Button>(R.id.closeInstructionsButton)
            closeBtn.setOnClickListener {
                finish() // Closes this activity and returns to MainActivity

        }
    }
}