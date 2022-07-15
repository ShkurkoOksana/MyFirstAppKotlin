package com.bignerdranch.android.notebookexample.controller

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bignerdranch.android.notebookexample.constants.IntentConstants
import com.bignerdranch.android.notebookexample.controller.databinding.ActivityEditBinding
import com.bignerdranch.android.notebookexample.database.MyDBManager
import java.text.SimpleDateFormat
import java.util.*

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private val myDBManager = MyDBManager(this)
    private var imageLauncher: ActivityResultLauncher<Intent>? = null
    private var tempImageUri: String = "empty"
    private var id = 0
    private var isEditState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initEditNotebookView()

        imageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                binding.imMainImage.setImageURI(it.data?.data)
                tempImageUri = it.data?.data.toString()
                contentResolver.takePersistableUriPermission(it.data?.data!!, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                binding.imMainImage.visibility = View.VISIBLE
            }
        }

        getMyIntent()
    }

    private fun getMyIntent() {
        val i = intent

        if (i != null) {
            if (i.getStringExtra(IntentConstants.INTENT_TITLE_KEY) != null) {
                isEditState = true
                binding.edTitile.isEnabled = false
                binding.edDesc.isEnabled = false
                binding.fbEditItem.visibility = View.VISIBLE
                binding.fbAddImage.visibility = View.GONE

                binding.apply {
                    edTitile.setText(i.getStringExtra(IntentConstants.INTENT_TITLE_KEY))
                    edDesc.setText(i.getStringExtra(IntentConstants.INTENT_DESCRIPTION_KEY))

                    id = i.getIntExtra(IntentConstants.INTENT_ID_KEY, 0)

                    if (i.getStringExtra(IntentConstants.INTENT_IMAGE_URI_KEY) != "empty") {
                        mainImageLayout.visibility = View.VISIBLE
                        fbAddImage.visibility = View.INVISIBLE
                        tempImageUri = i.getStringExtra(IntentConstants.INTENT_IMAGE_URI_KEY)!!
                        imMainImage.setImageURI(Uri.parse(tempImageUri))
                        imButtonDeleteImage.visibility = View.GONE
                        imButtomEditImage.visibility = View.GONE
                    } else {
                        mainImageLayout.visibility = View.GONE
                    }
                }

                binding.fbEditItem.setOnClickListener {
                    binding.edTitile.isEnabled = true
                    binding.edDesc.isEnabled = true

                    if (tempImageUri == "empty") {
                        binding.fbAddImage.visibility = View.VISIBLE
                    } else {
                        binding.fbAddImage.visibility = View.GONE
                    }


                    binding.imButtomEditImage.visibility = View.VISIBLE
                    binding.imButtonDeleteImage.visibility = View.VISIBLE
                    binding.fbEditItem.visibility = View.GONE
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        myDBManager.openDB()
    }

    override fun onDestroy() {
        super.onDestroy()
        myDBManager.closeDB()
    }

    private fun getCurrentTime(): String {
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd-MM-yy hh:mm", Locale.getDefault())
        return formatter.format(time)
    }

    private fun initEditNotebookView() {
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            fbAddImage.setOnClickListener {
                mainImageLayout.visibility = View.VISIBLE
                fbAddImage.visibility = View.GONE

                if (tempImageUri == "empty") {
                    imMainImage.visibility = View.GONE
                }
            }

            imButtonDeleteImage.setOnClickListener {
                mainImageLayout.visibility = View.GONE
                fbAddImage.visibility = View.VISIBLE
                tempImageUri = "empty"
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
                    if (isEditState) {
                        myDBManager.updateItemFromDB(id, title, desc, tempImageUri, getCurrentTime())

                    } else {
                        myDBManager.insertToDB(title, desc, tempImageUri, getCurrentTime())
                    }
                    finish()
                }
            }
        }
    }
}