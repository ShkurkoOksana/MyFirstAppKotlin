package com.bignerdranch.android.recycleviewexample.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.bignerdranch.android.recycleviewexample.databinding.ActivityPlantBookBinding

import com.bignerdranch.android.recycleviewexample.model.Plant

class PlantBookActivity : AppCompatActivity() {
    private lateinit var plantBookBinding: ActivityPlantBookBinding
    private val adapter = PlantAdapter()
    private var editLauncher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initPlanBookActivityViews()
    }

    private fun initPlanBookActivityViews() {
        plantBookBinding = ActivityPlantBookBinding.inflate(layoutInflater)
        setContentView(plantBookBinding.root)

        plantBookBinding.addButton.setOnClickListener {
            intent = Intent(this, PlantActivity::class.java)
            editLauncher?.launch(intent)
        }

        editLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                adapter.addPlant(it.data?.getSerializableExtra("plant") as Plant)
            }
        }

        plantBookBinding.apply {
            recycleView.layoutManager = GridLayoutManager(this@PlantBookActivity, 3)
            recycleView.adapter = adapter
        }
    }
}