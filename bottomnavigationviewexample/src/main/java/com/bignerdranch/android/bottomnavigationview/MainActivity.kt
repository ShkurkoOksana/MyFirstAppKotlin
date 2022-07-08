package com.bignerdranch.android.bottomnavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bignerdranch.android.bottomnavigationview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.bottomNavigationView.selectedItemId = R.id.profile

        mainBinding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show()
                R.id.search -> Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                R.id.favorite -> Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show()
                R.id.profile -> Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show()
            }

            true
        }
    }
}