package com.example.composedemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountViewModel : ViewModel() {
    // LiveData holds state which is observed by the UI
    // (state flows down from ViewModel)
    private val _count = MutableLiveData(0)
    val count: LiveData<Int> = _count

    // onNameChanged is an event we're defining that the UI can invoke
    // (events flow up from UI)
    fun onCountChanged(newCount: Int) {
        _count.value = newCount
    }
}