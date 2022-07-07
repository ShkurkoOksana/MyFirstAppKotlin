package com.bignerdranch.android.myfirstappkotlin.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bignerdranch.android.myfirstappkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private var launcher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initMainActivityViews()

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val text = result.data?.getStringExtra("answer")
                Log.d("MyLog", text.toString())
                mainBinding.sendAnswerText.visibility = View.VISIBLE
                mainBinding.sendAnswerText.text = text.toString()
            }
        }
    }

    private fun initMainActivityViews() {
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.questionButton.setOnClickListener {
            intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("question", mainBinding.questionText.text.toString())
            launcher?.launch(intent)
        }

        mainBinding.questionText.setOnClickListener {
            mainBinding.questionText.setText("")
        }
    }
}