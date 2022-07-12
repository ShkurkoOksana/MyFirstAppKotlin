package com.bignerdranch.android.fragmentexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel() {
    val messageForActivity: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val messageForFragment1: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val messageForFragment2: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}