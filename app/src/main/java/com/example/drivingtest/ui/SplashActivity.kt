package com.example.drivingtest.ui

import android.os.Handler
import com.example.drivingtest.base.BaseActivity
import com.example.drivingtest.databinding.ActivitySplashBinding
import com.example.drivingtest.utils.Common.openActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(
    ActivitySplashBinding::inflate
) {
    override fun initAction() {
        Handler().postDelayed({
            openActivity(this@SplashActivity, MainActivity::class.java)
        }, 3)
    }
}