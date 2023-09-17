package com.example.drivingtest.ui.examination.noti

import android.annotation.SuppressLint
import com.example.drivingtest.R
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentExamInfoBinding
import com.example.drivingtest.ui.examination.FragmentTakeExam
import com.example.drivingtest.ui.home.FragmentHome
import com.example.drivingtest.utils.Common

class FragmentExamInformation : BaseFragmentWithBinding<FragmentExamInfoBinding>(
    FragmentExamInfoBinding::inflate
) {
    companion object {
        fun newInstance(typeExam: String): FragmentExamInformation {
            val fragment = FragmentExamInformation()
            fragment.typeExam = typeExam
            Common.typeExam = typeExam
            return fragment
        }
    }

    var typeExam: String = ""

    @SuppressLint("SetTextI18n")
    override fun initAction() {
        binding.btnStartExam.setOnClickListener {
            Common.replaceFragment(
                requireActivity(),
                R.id.FragmentLayout,
                FragmentTakeExam.newInstance(typeExam = typeExam)
            )
        }

        binding.imgBackNotiEXam.setOnClickListener {
            Common.replaceFragment(
                requireActivity(),
                R.id.FragmentLayout,
                FragmentHome.newInstance()
            )
        }

        when (typeExam) {
            "A1,A2" -> {
                binding.tvTitleNotiExam.text = "Bài thi thử lý thuyết GPLX hạng A1, A2"
                binding.countQuestions.text = "Tổng số câu: 25"
                binding.timeExam.text = "Thời gian làm bài: 20 phút"
                binding.countQuesTrue.text = "Số câu đúng tối thiếu: 21/25"
            }

            "B1" -> {
                binding.tvTitleNotiExam.text = "Bài thi thử lý thuyết GPLX hạng B1"
                binding.countQuestions.text = "Tổng số câu: 30"
                binding.timeExam.text = "Thời gian làm bài: 20 phút"
                binding.countQuesTrue.text = "Số câu đúng tối thiếu: 27/30"
            }

            else -> {
                binding.tvTitleNotiExam.text = "Bài thi thử lý thuyết GPLX hạng B2"
                binding.countQuestions.text = "Tổng số câu: 35"
                binding.timeExam.text = "Thời gian làm bài: 22 phút"
                binding.countQuesTrue.text = "Số câu đúng tối thiếu: 32/35"
            }
        }
    }

    override fun initData() {
        /* */
    }
}