package com.bignerdranch.android.recycleviewexample.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.recycleviewexample.R
import com.bignerdranch.android.recycleviewexample.databinding.PlantItemBinding
import com.bignerdranch.android.recycleviewexample.model.Plant

class PlantAdapter : RecyclerView.Adapter<PlantAdapter.PlantHolder>() {
    val plantList = ArrayList<Plant>()

    class PlantHolder(item: View) : RecyclerView.ViewHolder(item) {
        val plantItemBinding = PlantItemBinding.bind(item)
        fun bind(plant: Plant) = with(plantItemBinding) {
            plantImage.setImageResource(plant.imageId)
            plantTitle.text = plant.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_item, parent, false)
        return PlantHolder(view)
    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        holder.bind(plantList[position])
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    fun addPlant(plant: Plant) {
        plantList.add(plant)
        notifyDataSetChanged()
    }
}