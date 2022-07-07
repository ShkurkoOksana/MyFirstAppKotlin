package com.bignerdranch.android.recycleviewexample.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.recycleviewexample.R
import com.bignerdranch.android.recycleviewexample.databinding.ActivityMainBinding
import com.bignerdranch.android.recycleviewexample.model.Plant

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    private val adapter = PlantAdapter()
    private val imageIdList = listOf(
        R.drawable.chamomile,
        R.drawable.dangeroud_plant,
        R.drawable.lilies,
        R.drawable.poppy,
        R.drawable.rose,
        R.drawable.vasilki)
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        init()
    }

    private fun init() = with(mainBinding) {
        recycleView.layoutManager = GridLayoutManager(this@MainActivity, 3)
        recycleView.adapter = adapter

        addButton.setOnClickListener {
            if (index > 5) index = 0

            val plant = Plant(imageIdList[index], "plant $index")
            adapter.addPlant(plant)
            index++
        }
    }
}