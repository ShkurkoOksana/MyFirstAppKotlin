package com.bignerdranch.android.toolbarexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // activate home arrow in toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // Change name of toolbar
        supportActionBar?.title = "Action Bar"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish() // if on toolbar push home arrow then do finish()
            R.id.sync -> Toast.makeText(this, "Sync", Toast.LENGTH_SHORT).show()
            R.id.save -> Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
            R.id.search -> Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}