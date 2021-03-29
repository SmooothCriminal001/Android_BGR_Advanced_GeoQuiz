package com.bignerdranch.android.advancedgeoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.ImageButton
import android.widget.Toast
import com.bignerdranch.android.geoquiz.Question

//A Tag to be used to easily identify 'MainActivity' related debug logs
//const means that this variable is compile-time immutable,
//only primitives are allowed as consts. The right hand side cannot be a function (that changes in runtime)
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    /*private lateinit var nextButton: Button
    private lateinit var previousButton: Button*/

    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton

    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
            Question(R.string.question_australia, true),
            Question(R.string.question_oceans, true),
            Question(R.string.question_mideast, false),
            Question(R.string.question_africa, false),
            Question(R.string.question_americas, true),
            Question(R.string.question_asia, true)
    )

    private var currentIndex = 0

    //Called when an activity is first setup, app is clicked on
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)

        setQuestion()

        trueButton.setOnClickListener {view: View ->
            //Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
            checkAnswer(true)
        }

        falseButton.setOnClickListener {view: View ->
            //Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
            checkAnswer(false)
        }

        nextButton.setOnClickListener {view: View ->
            goToNextQuestion()
        }

        questionTextView.setOnClickListener {view: View ->
            goToNextQuestion()
        }

        previousButton.setOnClickListener {view: View ->
            currentIndex = if(currentIndex == 0){
                questionBank.size - 1
            }else{
                currentIndex - 1
            }

            setQuestion()
        }
    }

    //On start of the activity, when it comes to split-screen/foreground
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    //When an activity gets to the foreground
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    //When an activity steps out of the strict foreground due to split screen, or a transparent overlay
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    //When an activity stops because of the user going to another app. Current app still lives in 'Recent apps'
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    //When the activity is destroyed. User clicks back button and gets out of app
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun goToNextQuestion() {
        currentIndex = (currentIndex + 1) % questionBank.size
        setQuestion()
    }

    private fun setQuestion(){
        val questionResId = questionBank[currentIndex].questionResId
        questionTextView.setText(questionResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val currentAnswer = questionBank[currentIndex].answer

        val messageResId = if(userAnswer == currentAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }

        showToastAtTop(Toast.makeText(this, messageResId, Toast.LENGTH_SHORT))
    }

    fun showToastAtTop(toast: Toast){
        toast.setGravity(Gravity.TOP, 0, 0)
        toast.show()
    }
}