package com.example.assignment2vishaygosai

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ScoreActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        // UI elements
        val txtResults = findViewById<TextView>(R.id.txtResults)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val txtScores = findViewById<TextView>(R.id.txtSores)
        val btnExit = findViewById<Button>(R.id.btnExit)
        val btnRestart = findViewById<Button>(R.id.btnRestart)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val txtScoreEnd = findViewById<TextView>(R.id.txtScoreEnd)

        // Restart button to Quiz
        btnRestart.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Exit button to terminate app
        btnExit.setOnClickListener {
            finishAffinity()
        }

        // Get the data from the Intent
        var questions = intent.getStringArrayExtra("questions") ?: emptyArray()
        var answers = intent.getBooleanArrayExtra("answers") ?: booleanArrayOf()
        var userResults = intent.getBooleanArrayExtra("userResults") ?: booleanArrayOf()

        // Review button
        btnReview.setOnClickListener {
            val scoreDetails = buildString {
                // Display the results
                questions.forEachIndexed { index, question ->
                    val result = if (answers[index] == userResults[index]) "Correct" else "Incorrect"
                    append("Question ${index + 1}: $question\n")
                    append("Your answer: ${if (userResults[index]) "True" else "False"} - $result\n")
                    append("Correct answer: ${if (answers[index]) "True" else "False"}\n\n")
                }
            }
            txtScoreEnd.text = scoreDetails
        }


        // Calculate the score
        var score = 0
        userResults?.forEach { if (it) score++ }
        txtScores.text = "Your Score: $score / ${questions?.size ?: 0}"

    }
}
