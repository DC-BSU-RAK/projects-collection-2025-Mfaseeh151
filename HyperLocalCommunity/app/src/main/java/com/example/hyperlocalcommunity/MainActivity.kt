package com.example.hyperlocalcommunity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    // Flag to control how long the splash screen stays
    private var isInitialContentReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        // Attach splash screen before super.onCreate
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Edge-to-edge insets setup
        val mainContainerView = findViewById<View>(R.id.main_container)
        if (mainContainerView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainContainerView) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
                insets
            }
        }

        // Keep splash screen until content is ready
        splashScreen.setKeepOnScreenCondition { !isInitialContentReady }

        // Simulate short loading delay (500ms)
        Handler(Looper.getMainLooper()).postDelayed({
            isInitialContentReady = true
        }, 500)

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)

        navView.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            var title = getString(R.string.app_name)

            when (item.itemId) {
                R.id.navigation_announcements -> {
                    selectedFragment = AnnouncementsFragment.newInstance()
                    title = getString(R.string.title_announcements)
                }
                R.id.navigation_skillshare -> {
                    selectedFragment = SkillShareFragment.newInstance()
                    title = getString(R.string.title_skillshare)
                }
            }

            selectedFragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it)
                    .commit()
                supportActionBar?.title = title
                true
            } ?: false
        }

        // Load default fragment only on initial creation
        if (savedInstanceState == null) {
            navView.selectedItemId = R.id.navigation_announcements
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            R.id.action_about -> {
                showAboutDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAboutDialog() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.about_dialog_title))
            .setMessage(getString(R.string.about_dialog_message))
            .setPositiveButton(getString(R.string.about_dialog_ok_button)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
