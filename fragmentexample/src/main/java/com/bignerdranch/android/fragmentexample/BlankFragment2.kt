package com.bignerdranch.android.fragmentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.bignerdranch.android.fragmentexample.databinding.Fragment1Binding
import com.bignerdranch.android.fragmentexample.databinding.Fragment2Binding

class BlankFragment2 : Fragment() {
    lateinit var fragment2Binding: Fragment2Binding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragment2Binding = Fragment2Binding.inflate(inflater)
        return fragment2Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment2Binding.buttonSendToFrag1.setOnClickListener {
            dataModel.messageForFragment1.value = "Hello from fragment2"
        }

        fragment2Binding.buttonSendToActivity.setOnClickListener {
            dataModel.messageForActivity.value = "Hello activity from fragment2"
        }

        dataModel.messageForFragment2.observe(activity as LifecycleOwner) {
            fragment2Binding.textMessage2.text = it
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment2()
    }
}