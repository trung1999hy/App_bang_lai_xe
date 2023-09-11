package com.example.drivingtest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentHomeViewModel : ViewModel() {
    private val _typeDriverList = MutableLiveData<List<String>>()
    val typeDriverList: LiveData<List<String>> get() = _typeDriverList

    init {
        _typeDriverList.value =
            arrayOf("Bằng lái A1", "Bằng lái A2", "Bằng lái B1", "Bằng lái B2").toList()
    }

}