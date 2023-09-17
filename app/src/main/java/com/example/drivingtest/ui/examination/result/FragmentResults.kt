package com.example.drivingtest.ui.examination.result

import android.annotation.SuppressLint
import com.example.drivingtest.R
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentResultsBinding
import com.example.drivingtest.ui.examination.noti.FragmentExamInformation
import com.example.drivingtest.ui.examination.result.reviewanswer.FragmentReviewAnswer
import com.example.drivingtest.ui.home.FragmentHome
import com.example.drivingtest.utils.Common

class FragmentResults : BaseFragmentWithBinding<FragmentResultsBinding>(
    FragmentResultsBinding::inflate
) {
    companion object {
        fun newInstance(resultExam: String, typeExam: String): FragmentResults {
            val fragment = FragmentResults()
            fragment.resultExam = resultExam
            fragment.typeExam = typeExam
            return fragment
        }
    }

    var resultExam: String = ""
    var typeExam: String = ""

    @SuppressLint("SetTextI18n")
    override fun initAction() {
        binding.imgExitCheckResult.setOnClickListener {
            Common.replaceFragment(
                requireActivity(),
                R.id.FragmentLayout,
                FragmentHome.newInstance()
            )
        }
        binding.tvScore.text =
            when (typeExam) {
                "A1,A2" -> "$resultExam/25"
                "B1" -> "$resultExam/30"
                else -> "$resultExam/35"
            }

        when (typeExam) {
            "A1,A2" -> binding.imgResult.setAnimation(if (resultExam.toInt() >= 21) R.raw.true_ani else R.raw.false_ani)
            "B1" -> binding.imgResult.setAnimation(if (resultExam.toInt() >= 27) R.raw.true_ani else R.raw.false_ani)
            else -> binding.imgResult.setAnimation(if (resultExam.toInt() >= 32) R.raw.true_ani else R.raw.false_ani)
        }

        binding.btnCheckAsk.setOnClickListener {
            Common.replaceFragment(
                requireActivity(),
                R.id.FragmentLayout,
                FragmentReviewAnswer.newInstance('t')
            )
        }

        binding.btnMoreExam.setOnClickListener {
            Common.replaceFragment(
                requireActivity(),
                R.id.FragmentLayout,
                FragmentExamInformation.newInstance(typeExam)
            )
        }
    }

    override fun initData() {

    }
}