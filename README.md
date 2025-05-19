App Overview 

This is an application made using Android Studios, coded using Kotlin about, a flash application on South African history using true or false. It shows the progress through five questions about South African history and it will show immediate feedback on whether the answer is correct and incorrect, it will also show the final mark the user gets out of five at the end with a review of the answers.

Features

- User Friendly Interface: Simple, intuitive design with straightforward navigation
- True/False Questions: Five questions about South African history
- Real Time Feedback: Immediate visual feedback for correct and incorrect answers
- Progress Tracking: Progress bar visualization and score display
- Result Review: Detailed breakdown of all answers at the end of the quiz
- Restart Option: Play again without exiting the app
Exit Function: Close the application when finished

Quiz Content

The quiz tests general knowledge about South Africa History in the format of 5 True / False questions:

1. Nelson Mandela was the first black president of South Africa. (True)
2. The apartheid system in South Africa officially ended in 1994. (True)
3. The Afrikaans language was originated from Dutch settlers in South Africa. (True)
4. Robin Island was used as a vacation spot during apartheid. (False)
5. The Union of South Africa was established in 1910. (True)




App Structure 

1. MainActivity (Start Screen)
Welcome message and app introduction ( txtHeading ) 
Instructions ( txtExplain )
Start button to begin the quiz ( btnStart )

2. QuizActivity (Quiz Screen)
Heading ( txtHeading2)
Progress bar showing quiz completion status ( progressBar )
Score tracking throughout the quiz ( txtScore )
Displays questions one at a time ( txtQuestion )
True and False buttons for answering ( btnTrue and btnFalse )
Colour-coded feedback, Magenta for correct, Red for incorrect ( txtFeadback )
Next button to advance to the following question ( btnNext )

3. ScoreActivity (Results Screen)
Heading ( txtResults )
Displays final score out of 5 ( txtScore )
Review button to see detailed feedback for each question ( btnReview )
Restart button to take the quiz again ( btnRestart )
Exit button to close the application ( btnExit )


Links

YouTube: https://youtu.be/TpNvjKVKfM8

GitHub:  https://github.com/st10478785/Assignment2_VishayGosai




References: 

Harvard reference link style :

By Anon Year: 2025 Container: Sharepoint.com URL: https://advtechonline.sharepoint.com/:w:/r/sites/TertiaryStudents/IIE Student Materials/New Student Materials CAT/IMAD5112/2025/Term 1/IMAD5112_MM.docx?d=wa1ff62f08e1a47bc99bdca07ae24427d&csf=1&web=1&e=3UqSH7

The independent Institution of Education, 2025. Introduction to mobile application development: Module manual 2025 (first edition). The independent Institution of education.



First Screen ( Activity Main )
Second Screen ( Activity Quiz )
Third Screen ( Activity Score )


Code:

First Page :
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



Second Page:
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


Third Page:
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
