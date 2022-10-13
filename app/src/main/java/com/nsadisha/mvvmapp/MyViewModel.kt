package com.nsadisha.mvvmapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyViewModel : ViewModel() {
    private var data:MutableLiveData<String>? = null
    var text: String? = null

    fun getData(input: String): LiveData<String>? {
        if (data == null || text != input) {
            text = input
            data?.let { update(it, input) }
        }
        return data
    }

    fun update(data: MutableLiveData<String>, input: String) {
        data.value = input
    }
//    fun update(t: String){
//        text = t
//    }
}