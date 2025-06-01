package com.example.hyperlocalcommunity

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsActivity : AppCompatActivity() {

    private lateinit var switchNotifications: SwitchMaterial
    private lateinit var sharedPreferences: SharedPreferences

    companion object {
        const val PREFS_NAME = "AppPrefs_HyperLocal_Kotlin_V2" // Ensure a unique name
        const val KEY_NOTIFICATIONS_ENABLED = "notificationsEnabled"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.settings_activity_title)
        }

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        switchNotifications = findViewById(R.id.switchNotifications)

        val notificationsEnabled = sharedPreferences.getBoolean(KEY_NOTIFICATIONS_ENABLED, true)
        switchNotifications.isChecked = notificationsEnabled

        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(KEY_NOTIFICATIONS_ENABLED, isChecked).apply()
        }
    }

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