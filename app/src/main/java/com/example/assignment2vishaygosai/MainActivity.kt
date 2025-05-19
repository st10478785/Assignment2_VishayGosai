package com.example.assignment2vishaygosai

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // UI elements
        val txtHeading = findViewById<TextView>(R.id.txtHeading)
        val txtExplain = findViewById<TextView>(R.id.txtExplain)
        val btnStart = findViewById<Button>(R.id.btnStart)

        // Link page to HomeActivity
        btnStart.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}