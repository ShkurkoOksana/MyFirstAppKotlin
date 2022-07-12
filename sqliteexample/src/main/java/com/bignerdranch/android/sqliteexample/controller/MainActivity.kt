package com.bignerdranch.android.sqliteexample.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.sqliteexample.database.MyDBManager
import com.bignerdranch.android.sqliteexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val myDBManager = MyDBManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            binding.textView.text = ""
            myDBManager.insertToDB(binding.textTitle.text.toString(), binding.textContent.text.toString())

            val dataList = myDBManager.readDBData()
            for (item in dataList) {
                binding.apply {
                    textView.append(item.title)
                    textView.append(" ")
                    textView.append(item.content)
                    textView.append("\n")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        myDBManager.openDB()
        val dataList = myDBManager.readDBData()
        for (item in dataList) {
            binding.apply {
                textView.append(item.title)
                textView.append(" ")
                textView.append(item.content)
                textView.append("\n")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDBManager.closeDB()
    }
}