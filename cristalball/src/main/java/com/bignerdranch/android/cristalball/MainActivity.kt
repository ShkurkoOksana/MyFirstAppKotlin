package com.bignerdranch.android.cristalball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.cristalball.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.messageText.setOnClickListener {
            binding.messageText.text = getJenda()
        }
    }

    private fun randomNumber(): Int {
        val size = resources.getStringArray(R.array.jenda).size - 1
        return (0..size).random()
    }

    private fun getJenda(): String {
        return resources.getStringArray(R.array.jenda)[randomNumber()]
    }
}