package com.example.drivingtest.ui

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import android.os.Handler
import com.example.drivingtest.R
import com.example.drivingtest.base.BaseActivity
import com.example.drivingtest.databinding.ActivityMainBinding
import com.example.drivingtest.ui.examination.noti.FragmentNotiExam
import com.example.drivingtest.ui.examination.result.checkresult.FragmentCheckResult
import com.example.drivingtest.ui.history.FragmentHistoryExam
import com.example.drivingtest.ui.home.FragmentHome
import com.example.drivingtest.ui.tippractice.FragmentTipsPractice
import com.example.drivingtest.ui.tippractice.expexam.FragmentExpExam
import com.example.drivingtest.utils.Common

class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {
    private var isOnBackPress = 0

    @SuppressLint("CommitTransaction")
    override fun initAction() {
        Common.addFragment(this@MainActivity, R.id.FragmentLayout, FragmentHome.newInstance())
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.FragmentLayout)
        if (fragment !is FragmentHome) {
            when (fragment) {
                is FragmentExpExam -> {
                    Common.replaceFragment(
                        this@MainActivity,
                        R.id.FragmentLayout,
                        FragmentTipsPractice.newInstance(Common.typeTipsPractice)
                    )
                }

                is FragmentCheckResult -> {
                    if (Common.from == 'l') {
                        Common.replaceFragment(
                            this@MainActivity,
                            R.id.FragmentLayout,
                            FragmentHistoryExam.newInstance()
                        )
                    } else {
                        Common.replaceFragment(
                            this@MainActivity,
                            R.id.FragmentLayout,
                            FragmentNotiExam.newInstance(Common.typeExam)
                        )
                    }
                }

                else -> {
                    Common.replaceFragment(
                        this@MainActivity,
                        R.id.FragmentLayout,
                        FragmentHome.newInstance()
                    )
                }
            }
        } else {
            if (Common.isCheckSwipe >= 1) {
                Common.replaceFragment(
                    this@MainActivity,
                    R.id.FragmentLayout,
                    FragmentHome.newInstance()
                )
                Common.isCheckSwipe = 0
            } else {
                isOnBackPress++
                Common.createCustomToast(
                    this@MainActivity,
                    layoutInflater
                )
                Handler().postDelayed({
                    isOnBackPress = 0
                }, 3000)
                if (isOnBackPress == 2) {
                    finish()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        isOnBackPress = 0
    }

    override fun onRestart() {
        super.onRestart()
        isOnBackPress = 0
    }
}
