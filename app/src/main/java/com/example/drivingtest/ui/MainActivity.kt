package com.example.drivingtest.ui

import android.annotation.SuppressLint
import android.os.Handler
import com.example.drivingtest.R
import com.example.drivingtest.base.BaseActivity
import com.example.drivingtest.databinding.ActivityMainBinding
import com.example.drivingtest.ui.examination.noti.FragmentExamInformation
import com.example.drivingtest.ui.examination.result.reviewanswer.FragmentReviewAnswer
import com.example.drivingtest.ui.history.FragmentExamHistory
import com.example.drivingtest.ui.home.FragmentHome
import com.example.drivingtest.ui.tippractice.FragmentPracticeTips
import com.example.drivingtest.ui.tippractice.expexam.FragmentExamExperience
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
                is FragmentExamExperience -> {
                    Common.replaceFragment(
                        this@MainActivity,
                        R.id.FragmentLayout,
                        FragmentPracticeTips.newInstance(Common.typeTipsPractice)
                    )
                }

                is FragmentReviewAnswer -> {
                    if (Common.from == 'l') {
                        Common.replaceFragment(
                            this@MainActivity,
                            R.id.FragmentLayout,
                            FragmentExamHistory.newInstance()
                        )
                    } else {
                        Common.replaceFragment(
                            this@MainActivity,
                            R.id.FragmentLayout,
                            FragmentExamInformation.newInstance(Common.typeExam)
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
