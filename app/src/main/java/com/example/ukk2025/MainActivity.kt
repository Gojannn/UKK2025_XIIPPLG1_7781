package com.example.ukk2025

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.ukk2025.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_tampilan)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main) as NavHostFragment
        navHostFragment.navController

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@MainActivity, MainActivity::class.java))
            finish()
        }, 3000)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}