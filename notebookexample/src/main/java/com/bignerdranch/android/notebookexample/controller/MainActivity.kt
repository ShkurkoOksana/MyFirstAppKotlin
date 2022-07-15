package com.bignerdranch.android.notebookexample.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.notebookexample.controller.databinding.ActivityMainBinding
import com.bignerdranch.android.notebookexample.database.MyDBManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val myDBManager = MyDBManager(this)
    val myAdapter = NotebookAdapter(ArrayList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        binding.fbNew.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
    }

    fun init() {
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = myAdapter
    }

    fun fillAdapter() {
        myAdapter.updateAdapter(myDBManager.readDBData())
    }

    override fun onResume() {
        super.onResume()
        myDBManager.openDB()
        fillAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDBManager.closeDB()
    }
}