package com.bignerdranch.android.myfirstappkotlin.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bignerdranch.android.myfirstappkotlin.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {
    private lateinit var questionBinding: ActivityQuestionBinding
    private var launcher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initQuestionActivityViews()

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val text = result.data?.getStringExtra("answer")
                Log.d("MyLog", text.toString())
                questionBinding.sendAnswerText.visibility = View.VISIBLE
                questionBinding.sendAnswerText.text = text.toString()
            }
        }
    }

    private fun initQuestionActivityViews() {
        questionBinding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(questionBinding.root)

        questionBinding.questionButton.setOnClickListener {
            intent = Intent(this, AnswerActivity::class.java)
            intent.putExtra("question", questionBinding.questionText.text.toString())
            launcher?.launch(intent)
        }

        questionBinding.questionText.setOnClickListener {
            questionBinding.questionText.setText("")
        }
    }
}