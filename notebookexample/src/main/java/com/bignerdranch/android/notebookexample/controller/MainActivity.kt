package com.bignerdranch.android.notebookexample.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bignerdranch.android.notebookexample.controller.databinding.ActivityMainBinding
import com.bignerdranch.android.notebookexample.database.MyDBManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val myDBManager = MyDBManager(this)
    private var launcher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fbNew.setOnClickListener {
            intent = Intent(this, EditActivity::class.java)
            launcher?.launch(intent)
        }

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
            }
        }

/*        binding.button.setOnClickListener {
            binding.textView.text = ""
            myDBManager.insertToDB(binding.textTitle.text.toString(), binding.textContent.text.toString())

            val dataList = myDBManager.readDBData()
            for (item in dataList) {
                binding.apply {
                    textView.append(item.title)
                    textView.append(" ")
                    textView.append(item.content)
                    textView.append("\n")
                }
            }
        }*/
    }

    override fun onResume() {
        super.onResume()

        myDBManager.openDB()
        val dataList = myDBManager.readDBData()
        for (item in dataList) {
            binding.apply {
/*                textView.append(item.title)
                textView.append(" ")
                textView.append(item.content)
                textView.append("\n")*/
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDBManager.closeDB()
    }
}