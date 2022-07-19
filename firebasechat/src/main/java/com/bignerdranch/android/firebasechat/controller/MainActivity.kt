package com.bignerdranch.android.firebasechat.controller

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.firebasechat.R
import com.bignerdranch.android.firebasechat.databinding.ActivityMainBinding
import com.bignerdranch.android.firebasechat.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private var auth = Firebase.auth
    private val node = "message"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()

        onFireBaseChangeListener(getReferenceOfFireBase(node))

        initRecycleView()
    }

    private fun initRecycleView() = with(binding) {
        adapter = UserAdapter()

        rcView.layoutManager = LinearLayoutManager(this@MainActivity)

        rcView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sign_out) {
            auth.signOut()
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btSend.setOnClickListener {
                sendMessageToFireBase(getReferenceOfFireBase(node), User(auth.currentUser?.displayName, edMessage.text.toString()))
            }
        }

        setUpActionBar()
    }

    private fun getReferenceOfFireBase(node: String): DatabaseReference {
        val database = Firebase.database
        val myRef = database.getReference(node)

        return myRef
    }

    private fun sendMessageToFireBase(dbRef: DatabaseReference, user: User) {
        dbRef.child(dbRef.push().key ?: "bla-bla").setValue(user)
    }

    private fun onFireBaseChangeListener(dbRef: DatabaseReference) {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<User>()

                for (s in snapshot.children) {
                    val user = s.getValue(User::class.java)

                    if (user != null) {
                        list.add(user)
                    }
                }

                adapter.submitList(list)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun setUpActionBar() {
        val actionBar = supportActionBar

        Thread {
            val bitMap = Picasso.get().load(auth.currentUser?.photoUrl).get()
            val drawableIcon = BitmapDrawable(resources, bitMap)
            runOnUiThread {
                actionBar?.setDisplayHomeAsUpEnabled(true)
                actionBar?.setHomeAsUpIndicator(drawableIcon)
                actionBar?.title = auth.currentUser?.displayName
            }
        }.start()
    }
}