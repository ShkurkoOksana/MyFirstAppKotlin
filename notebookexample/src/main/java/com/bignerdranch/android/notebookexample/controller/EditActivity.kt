package com.bignerdranch.android.notebookexample.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.notebookexample.controller.databinding.ActivityEditBinding


class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}