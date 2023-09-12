package com.example.drivingtest.ui.home

import android.content.Context
import android.widget.ArrayAdapter

class SpinnerTypeAdapter(context: Context, resource: Int, objects: List<String>) :
    ArrayAdapter<String>(context, resource, objects) {

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): String? {
        return super.getItem(position)
    }
}