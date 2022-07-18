package com.bignerdranch.android.firebasechat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.firebasechat.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val node = "message"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()

        onFireBaseChangeListener(getReferenceOfFireBase(node))
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btSend.setOnClickListener {
                sendMessageToFireBase(getReferenceOfFireBase(node), edMessage.text.toString())
            }
        }
    }

    private fun getReferenceOfFireBase(node: String): DatabaseReference {
        val database = Firebase.database
        val myRef = database.getReference(node)

        return myRef
    }

    private fun sendMessageToFireBase(dbRef: DatabaseReference, message: String) {
        dbRef.setValue(message)
    }

    private fun onFireBaseChangeListener(dbRef: DatabaseReference) {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.apply {
                    rcView.append("\n")

                    if(snapshot.value != null) {
                        rcView.append("Oksana: ${snapshot.value.toString()}")
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}