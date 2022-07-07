package com.bignerdranch.android.recycleviewexample.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bignerdranch.android.recycleviewexample.R
import com.bignerdranch.android.recycleviewexample.databinding.ActivityPlantBinding
import com.bignerdranch.android.recycleviewexample.model.Plant

class PlantActivity : AppCompatActivity() {
    private lateinit var plantBinding: ActivityPlantBinding
    private val imageIdList = listOf(
        R.drawable.chamomile,
        R.drawable.dangeroud_plant,
        R.drawable.lilies,
        R.drawable.poppy,
        R.drawable.rose,
        R.drawable.vasilki
    )
    private var index: Int = 0
    private var imageId = R.drawable.chamomile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPlantActivityViews()
    }

    private fun initPlantActivityViews() {
        plantBinding = ActivityPlantBinding.inflate(layoutInflater)
        setContentView(plantBinding.root)

        plantBinding.nextImageButton.setOnClickListener {
            index++

            if (index > 5) index = 0
            plantBinding.plantImage.setImageResource(imageIdList[index])
            imageId = imageIdList[index]
        }

        plantBinding.doneButton.setOnClickListener {
            plantBinding.apply {
                val plant = Plant(imageId, plantTextTitle.text.toString(), plantTextDescription.text.toString())
                val intent = Intent()
                intent.putExtra("plant", plant)
                setResult(RESULT_OK, intent)
                finish()
            }
        }
    }
}