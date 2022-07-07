package com.bignerdranch.android.myfirstappkotlin.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bignerdranch.android.myfirstappkotlin.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var secondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSecondActivityViews()

        val question = intent.getStringExtra("question").toString()
        secondBinding.sendQuestionText.text = question
    }

    private fun initSecondActivityViews() {
        secondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(secondBinding.root)

        secondBinding.answerButton.setOnClickListener {
            intent.putExtra("answer", secondBinding.answerText.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}