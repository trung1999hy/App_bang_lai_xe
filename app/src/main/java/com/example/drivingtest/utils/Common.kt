package com.example.drivingtest.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.drivingtest.R
import com.example.drivingtest.databinding.ToastCustomBinding

object Common {
    fun openActivity(activity: Activity, destinationClass: Class<*>) {
        activity.startActivity(Intent(activity.application, destinationClass))
        activity.overridePendingTransition(R.anim.dim_in, R.anim.dim_out)
        activity.finish()
    }

    fun addFragment(activity: Activity, id: Int, fragment: Fragment) {
        (activity as AppCompatActivity).supportFragmentManager.beginTransaction()
            .add(id, fragment)
            .setCustomAnimations(R.anim.fade_in, R.anim.slide_out)
            .commit()
    }

    @SuppressLint("SetTextI18n")
    fun createCustomToast(
        activity: Activity,
        layoutInflater: LayoutInflater,
    ) {
        val toast = Toast(activity)
        toast.apply {
            val mBinding = ToastCustomBinding.inflate(layoutInflater)
            mBinding.tvMessageCustomToast.text = "Nhấn quay lại một lần nữa để thoát ứng dụng"
            duration = Toast.LENGTH_SHORT
            view = mBinding.root
            show()
        }
    }
}