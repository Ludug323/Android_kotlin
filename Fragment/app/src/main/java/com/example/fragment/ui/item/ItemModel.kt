package com.example.fragment.ui.item


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Item Fragment"
    }
    val text: LiveData<String> = _text
}