package com.example.drivingtest.ui.home

import android.os.Handler
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.core.view.size
import androidx.fragment.app.Fragment
import com.example.drivingtest.R
import com.example.drivingtest.databinding.ActivityMainBinding
import com.example.drivingtest.base.BaseActivity
import com.example.drivingtest.ui.examination.FragmentExamination
import com.example.drivingtest.ui.history.FragmentHistoryExam
import com.example.drivingtest.ui.signboard.FragmentSignBoard
import com.example.drivingtest.ui.theory.FragmentTheory
import com.example.drivingtest.ui.tips.FragmentTips
import com.example.drivingtest.ui.tipsignboard.FragmentTipsSignBoard
import com.example.drivingtest.utils.Common
import com.example.drivingtest.utils.Common.addFragment


class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {
    private val viewModel: MainViewModel by viewModels()
    private var isOnBackPress = 0

    override fun initAction() {
        val adapter = SpinnerTypeAdapter(
            this@MainActivity,
            android.R.layout.simple_spinner_item,
            viewModel.typeDriverList.value ?: return
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.SpinnerType.adapter = adapter

        binding.SpinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                binding.ToolBarHome.title = "Ôn thi ${viewModel.typeDriverList.value!![position]}"
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.LearnTheory.setOnClickListener {
            addFragmentToMain(FragmentTheory.newInstance())
        }

        binding.Examination.setOnClickListener {
            addFragmentToMain(FragmentExamination.newInstance())
        }

        binding.TipsPractice.setOnClickListener {
            addFragmentToMain(FragmentTipsSignBoard.newInstance())
        }

        binding.SignBoard.setOnClickListener {
            addFragmentToMain(FragmentSignBoard.newInstance())
        }

        binding.Tips.setOnClickListener {
            addFragmentToMain(FragmentTips.newInstance())
        }

        binding.HistoryExam.setOnClickListener {
            addFragmentToMain(FragmentHistoryExam.newInstance())
        }
    }

    private fun addFragmentToMain(fragment: Fragment) {
        if (binding.OverallLayout.visibility != View.GONE) {
            binding.OverallLayout.visibility = View.GONE
            binding.FragmentLayout.visibility = View.VISIBLE
            addFragment(this@MainActivity, R.id.FragmentLayout, fragment)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (binding.FragmentLayout.size >= 1) {
            binding.OverallLayout.visibility = View.VISIBLE
            binding.FragmentLayout.removeViewAt(0)
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

    override fun onResume() {
        super.onResume()
        isOnBackPress = 0
    }

    override fun onRestart() {
        super.onRestart()
        isOnBackPress = 0
    }
}
