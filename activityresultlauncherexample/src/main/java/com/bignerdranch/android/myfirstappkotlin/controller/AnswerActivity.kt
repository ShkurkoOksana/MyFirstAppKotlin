package com.bignerdranch.android.myfirstappkotlin.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.myfirstappkotlin.databinding.ActivityAnswerBinding

class AnswerActivity : AppCompatActivity() {
    private lateinit var answerBinding: ActivityAnswerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initAnswerActivityViews()
        updateAnswerActivityViews()
    }

    private fun updateAnswerActivityViews() {
        val question = intent.getStringExtra("question").toString()
        answerBinding.sendQuestionText.text = question
    }

    private fun initAnswerActivityViews() {
        answerBinding = ActivityAnswerBinding.inflate(layoutInflater)
        setContentView(answerBinding.root)

        answerBinding.answerButton.setOnClickListener {
            intent.putExtra("answer", answerBinding.answerText.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}