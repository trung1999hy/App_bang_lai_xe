package com.example.drivingtest.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.drivingtest.R
import com.example.drivingtest.databinding.CustomToastBinding

object Common {
    var typeTipsPractice: String = ""
    var from: Char? = null
    var typeExam: String = ""
    var isCheckSwipe: Int = 0

    fun openActivity(activity: Activity, destinationClass: Class<*>) {
        activity.startActivity(Intent(activity.application, destinationClass))
        activity.overridePendingTransition(R.anim.dim_in, R.anim.dim_out)
        activity.finish()
    }

    fun addFragment(activity: Activity, id: Int, fragment: Fragment) {
        (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
            .add(id, fragment)
            .commit()
    }

    fun replaceFragment(activity: Activity, id: Int, fragment: Fragment) {
        (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
            .replace(id, fragment)
            .addToBackStack(null)
            .commit()
    }

    @SuppressLint("SetTextI18n")
    fun createCustomToast(
        activity: Activity,
        layoutInflater: LayoutInflater,
    ) {
        val toast = Toast(activity)
        toast.apply {
            val mBinding = CustomToastBinding.inflate(layoutInflater)
            mBinding.tvMessageCustomToast.text = "Nhấn quay lại một lần nữa để thoát ứng dụng"
            duration = Toast.LENGTH_SHORT
            view = mBinding.root
            show()
        }
    }
}