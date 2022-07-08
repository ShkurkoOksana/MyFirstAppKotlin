package com.bignerdranch.android.drawerlayoutexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.bignerdranch.android.drawerlayoutexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.apply {
            navigationView.setNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.icon1 -> Toast.makeText(this@MainActivity, "icon1", Toast.LENGTH_SHORT).show()
                    R.id.icon2 -> Toast.makeText(this@MainActivity, "icon2", Toast.LENGTH_SHORT).show()
                    R.id.icon3 -> Toast.makeText(this@MainActivity, "icon3", Toast.LENGTH_SHORT).show()
                    R.id.icon4 ->Toast.makeText(this@MainActivity, "icon4", Toast.LENGTH_SHORT).show()
                    R.id.icon5 ->Toast.makeText(this@MainActivity, "icon5", Toast.LENGTH_SHORT).show()
                    R.id.icon6 ->Toast.makeText(this@MainActivity, "icon6", Toast.LENGTH_SHORT).show()
                }
                drawer.closeDrawer(GravityCompat.START)
                true
            }

            openButton.setOnClickListener {
                drawer.openDrawer(GravityCompat.START)
            }
        }
    }
}