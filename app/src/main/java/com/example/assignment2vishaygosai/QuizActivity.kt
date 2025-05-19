package com.example.assignment2vishaygosai

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


class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        // UI elements
        val txtQuestion = findViewById<TextView>(R.id.txtQuestion)
        val txtScore = findViewById<TextView>(R.id.txtScore)
        val txtFeedback = findViewById<TextView>(R.id.txtFeedback)
        val btnTrue = findViewById<Button>(R.id.btnTrue)
        val btnFalse = findViewById<Button>(R.id.btnFalse)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        // Arrays for questions and answers
        // Questions
        val questions = arrayOf(
            "Nelson Mandela was the first black president of South Africa.",
            "The apartheid system in South Africa officially ended in 1994.",
            "The Afrikaans language was originated from Dutch settlers in South Africa.",
            "Robin Island was used as a vacation spot during apartheid.",
            " The Union of South Africa was established in 1910."
        )

        // True or false answers
        val answers = booleanArrayOf(true, true, true, false, true)
        val userResults = BooleanArray(questions.size) { false }

        // Initialize variables
        var currentQuestion = 0
        var score = 0

        // Functions for quiz
        fun showFeedback(isCorrect: Boolean) {
            txtFeedback.visibility = View.VISIBLE
            if (isCorrect) {
                txtFeedback.text = "Correct! Well done!"
                txtFeedback.setTextColor(Color.MAGENTA)
            } else {
                txtFeedback.text =
                    "Incorrect! The answer was ${if (answers[currentQuestion]) "True" else "False"}."
                txtFeedback.setTextColor(Color.RED)
            }
            btnNext.visibility = View.VISIBLE
            btnTrue.visibility = View.GONE
            btnFalse.visibility = View.GONE
        }

        // Function to update the UI elements
        fun updateUI() {
            // Progress bar update
            progressBar.progress = currentQuestion + 1
            txtScore.text = "Score: $score / ${questions.size}"

            // Change the text of the next button to 'Finish' when all question are answered.
            if (currentQuestion == questions.size - 1) {
                btnNext.text = "Finish"
            } else {
                btnNext.text = "Next"
            }
        }

        // Button True
        btnTrue.setOnClickListener {
            val isCorrect = answers[currentQuestion]
            userResults[currentQuestion] = isCorrect
            showFeedback(isCorrect)
            updateUI()
        }

        // Button False
        btnFalse.setOnClickListener {
            val isCorrect = !answers[currentQuestion]
            userResults[currentQuestion] = isCorrect
            showFeedback(isCorrect)
            updateUI()
        }

        // Set the progress bar max and first question
        progressBar.max = questions.size
        progressBar.progress = 1
        txtQuestion.text = questions[currentQuestion]
        updateUI()

        // Button Next
        btnNext.setOnClickListener {
            if (currentQuestion < questions.size - 1) {
                // If there are more questions, display the next question
                currentQuestion++
                txtFeedback.visibility = View.GONE
                btnNext.visibility = View.GONE
                btnTrue.visibility = View.VISIBLE
                btnFalse.visibility = View.VISIBLE
                txtFeedback.text = ""
                txtQuestion.text = questions[currentQuestion]
                updateUI()
            } else {
                // Calculate final score using a for loop
                score = 0
                for (i in userResults.indices) {
                    if (userResults[i]) {
                        score++
                    }
                }

                // Update the score display one last time
                txtScore.text = "Score: $score / ${questions.size}"

                // Finish button must take you to ScoreActivity
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("questions", questions)
                intent.putExtra("answers", answers)
                intent.putExtra("userResults", userResults)
                startActivity(intent)
                finish()
            }
        }
    }
}

