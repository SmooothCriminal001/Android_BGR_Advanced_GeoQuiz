package com.bignerdranch.android.advancedgeoquiz

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.geoquiz.Question

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel(){

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    var currentIndex = 0

    val currentAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionId: Int
        get() = questionBank[currentIndex].questionResId

    var answeredStatus: Boolean = false
        get() = questionBank[currentIndex].isAnswered
        set(value){
            field = value

            questionBank[currentIndex].isAnswered = value
        }

    fun makeNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun makePrevious(){
        currentIndex = if(currentIndex == 0) questionBank.size - 1 else currentIndex - 1
    }

    fun setScore(){
        questionBank[currentIndex].score = 1
    }

    fun getResult(): Int?{
        var percentage: Int? = null

        if(questionBank.all { it.isAnswered }) {
            val totalScore = questionBank.fold(0) { acc, question -> acc + question.score }
            percentage = ((totalScore.toDouble() / questionBank.size) * 100).toInt()
        }

        return percentage
    }
}