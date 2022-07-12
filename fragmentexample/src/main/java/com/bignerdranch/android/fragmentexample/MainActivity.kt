package com.bignerdranch.android.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.bignerdranch.android.fragmentexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    private val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        openFragment(BlankFragment.newInstance(), R.id.place_holder)
        openFragment(BlankFragment2.newInstance(), R.id.place_holder2)

        dataModel.messageForActivity.observe(this) {
            mainBinding.textView.text = it
        }
    }

    private fun openFragment(fragment: Fragment, idHolder: Int) {
        supportFragmentManager.beginTransaction().replace(idHolder, fragment).commit()
    }
}