package com.bignerdranch.android.fragmentexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.bignerdranch.android.fragmentexample.databinding.Fragment1Binding

class BlankFragment : Fragment() {
    lateinit var fragment1Binding: Fragment1Binding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragment1Binding = Fragment1Binding.inflate(inflater)
        return fragment1Binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragment1Binding.ButtonSendToFrag2.setOnClickListener {
            dataModel.messageForFragment2.value = "Hello from fragment1"
        }

        fragment1Binding.buttonSendToActivity.setOnClickListener {
            dataModel.messageForActivity.value = "Hello activity from fragment1"
        }

        dataModel.messageForFragment1.observe(activity as LifecycleOwner) {
            fragment1Binding.textMessage1.text = it
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}