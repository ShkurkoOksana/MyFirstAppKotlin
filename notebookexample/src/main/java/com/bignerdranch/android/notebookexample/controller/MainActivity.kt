package com.bignerdranch.android.notebookexample.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        initSearchView()

        binding.fbNew.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {
        binding.rcView.layoutManager = LinearLayoutManager(this)
        val swapHelper = getSwapMg()
        swapHelper.attachToRecyclerView(binding.rcView)
        binding.rcView.adapter = myAdapter
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                val list = myDBManager.readDBData(p0!!)
                myAdapter.updateAdapter(list)
                return true
            }

        })
    }

    private fun fillAdapter() {
        val list = myDBManager.readDBData("")
        myAdapter.updateAdapter(list)

        if (list.size > 0) {
            binding.tvNoElements.visibility = View.GONE
        }
    }

    private fun getSwapMg(): ItemTouchHelper {
        return ItemTouchHelper(object: ItemTouchHelper.
        SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myAdapter.removeItem(viewHolder.adapterPosition, myDBManager)
            }

        })
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