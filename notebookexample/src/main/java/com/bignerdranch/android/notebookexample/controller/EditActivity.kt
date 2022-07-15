package com.bignerdranch.android.notebookexample.controller

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bignerdranch.android.notebookexample.constants.IntentConstants
import com.bignerdranch.android.notebookexample.controller.databinding.ActivityEditBinding
import com.bignerdranch.android.notebookexample.database.MyDBManager

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private val myDBManager = MyDBManager(this)
    private var imageLauncher: ActivityResultLauncher<Intent>? = null
    private var tempImageUri: String = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initEditNotebookView()

        imageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                binding.imMainImage.setImageURI(it.data?.data)
                tempImageUri = it.data?.data.toString()
                contentResolver.takePersistableUriPermission(it.data?.data!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
        }

        getMyIntent()
    }

    fun getMyIntent() {
        val i = intent

        if (i != null) {
            if (i.getStringExtra(IntentConstants.INTENT_TITLE_KEY) != null) {
                binding.apply {
                    fbAddImage.visibility = View.VISIBLE

                    edTitile.setText(i.getStringExtra(IntentConstants.INTENT_TITLE_KEY))
                    edDesc.setText(i.getStringExtra(IntentConstants.INTENT_DESCRIPTION_KEY))

                    if (i.getStringExtra(IntentConstants.INTENT_IMAGE_URI_KEY) != "empty") {
                        mainImageLayout.visibility = View.VISIBLE
                        imMainImage.setImageURI(Uri.parse(i.getStringExtra(IntentConstants.INTENT_IMAGE_URI_KEY)))
                        imButtonDeleteImage.visibility = View.GONE
                        imButtomEditImage.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        myDBManager.openDB()
        val dataList = myDBManager.readDBData()
        for (item in dataList) {
            binding.apply {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        myDBManager.closeDB()
    }

    private fun initEditNotebookView() {
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            fbAddImage.setOnClickListener {
                mainImageLayout.visibility = View.VISIBLE
                fbAddImage.visibility = View.GONE
            }

            imButtonDeleteImage.setOnClickListener {
                mainImageLayout.visibility = View.GONE
                fbAddImage.visibility = View.VISIBLE
            }

            imButtomEditImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.type = "image/*"
                imageLauncher?.launch(intent)
            }

            fbSave.setOnClickListener {
                var title = binding.edTitile.text.toString()
                var desc = binding.edDesc.text.toString()

                if (title != "" && desc != "") {
                    myDBManager.insertToDB(title, desc, tempImageUri)
                    finish()
                }
            }
        }
    }
}