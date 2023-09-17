package com.example.drivingtest.ui.tippractice

import android.view.View
import com.example.drivingtest.R
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentPracticeTipBinding
import com.example.drivingtest.ui.home.FragmentHome
import com.example.drivingtest.ui.tippractice.expexam.FragmentExamExperience
import com.example.drivingtest.utils.Common

class FragmentPracticeTips : BaseFragmentWithBinding<FragmentPracticeTipBinding>(
    FragmentPracticeTipBinding::inflate
) {
    companion object {
        fun newInstance(typeTipsPractice: String): FragmentPracticeTips {
            val fragment = FragmentPracticeTips()
            fragment.typeTipsPractice = typeTipsPractice
            return fragment
        }
    }

    var typeTipsPractice: String = ""

    override fun initAction() {
        if (typeTipsPractice == "A1,A2") {
            binding.FrlA.visibility = View.VISIBLE
            binding.FrlB.visibility = View.GONE
        } else {
            binding.FrlA.visibility = View.GONE
            binding.FrlB.visibility = View.VISIBLE
        }

        binding.tvExpExamPraA.setOnClickListener { moveToExpExam("A1,A2") }
        binding.tvExpExamPraB.setOnClickListener { moveToExpExam("B1,B2") }

        binding.imgBackTipPraA.setOnClickListener {
            Common.replaceFragment(
                requireActivity(),
                R.id.FragmentLayout,
                FragmentHome.newInstance()
            )
        }
        binding.imgBackTipPraB.setOnClickListener {
            Common.replaceFragment(
                requireActivity(),
                R.id.FragmentLayout,
                FragmentHome.newInstance()
            )
        }
    }

    override fun initData() {

    }

    private fun moveToExpExam(type: String) {
        Common.replaceFragment(
            requireActivity(),
            R.id.FragmentLayout,
            FragmentExamExperience.newInstance(type)
        )
    }
}