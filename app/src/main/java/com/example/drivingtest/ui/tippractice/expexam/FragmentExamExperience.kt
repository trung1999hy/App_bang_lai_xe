package com.example.drivingtest.ui.tippractice.expexam

import android.annotation.SuppressLint
import android.view.View
import com.example.drivingtest.R
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentExamExperienceBinding
import com.example.drivingtest.ui.tippractice.FragmentPracticeTips
import com.example.drivingtest.utils.Common

class FragmentExamExperience : BaseFragmentWithBinding<FragmentExamExperienceBinding>(
    FragmentExamExperienceBinding::inflate
) {
    companion object {
        fun newInstance(typeDriving: String): FragmentExamExperience {
            val fragment = FragmentExamExperience()
            fragment.typeDriving = typeDriving
            Common.typeTipsPractice = typeDriving
            return fragment
        }
    }

    var typeDriving: String = ""

    @SuppressLint("SetTextI18n")
    override fun initAction() {
        if (typeDriving == "A1,A2") {
            binding.FrlExpA.visibility = View.VISIBLE
            binding.FrlExpB.visibility = View.GONE
            binding.tvBackTipsPraA.text = "<< Quay lại trang trước"
            binding.tvBackTipsPraA.setOnClickListener {
                Common.replaceFragment(
                    requireActivity(),
                    R.id.FragmentLayout,
                    FragmentPracticeTips.newInstance(typeTipsPractice = typeDriving)
                )
            }
        } else {
            binding.FrlExpA.visibility = View.GONE
            binding.FrlExpB.visibility = View.VISIBLE
            binding.tvBackTipsPraB.text = "<< Quay lại trang trước"
            binding.tvBackTipsPraB.setOnClickListener {
                Common.replaceFragment(
                    requireActivity(),
                    R.id.FragmentLayout,
                    FragmentPracticeTips.newInstance(typeTipsPractice = typeDriving)
                )
            }
        }
    }

    override fun initData() {

    }
}