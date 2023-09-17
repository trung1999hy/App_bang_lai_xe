package com.example.drivingtest.ui.examination.result.reviewanswer

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drivingtest.R
import com.example.drivingtest.adapter.ReviewAnswerAdapter
import com.example.drivingtest.adapter.ExamHistoryAdapter
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentReviewAnswerBinding
import com.example.drivingtest.model.QuestionsModel
import com.example.drivingtest.ui.examination.FragmentTakeExam
import com.example.drivingtest.ui.examination.FragmentTakeExam.Companion.SIZE
import com.example.drivingtest.ui.examination.noti.FragmentExamInformation
import com.example.drivingtest.ui.history.FragmentExamHistory
import com.example.drivingtest.utils.Common

class FragmentReviewAnswer : BaseFragmentWithBinding<FragmentReviewAnswerBinding>(
    FragmentReviewAnswerBinding::inflate
) {
    companion object {
        fun newInstance(from: Char? = null, pos: Int ?= null): FragmentReviewAnswer {
            val fragment = FragmentReviewAnswer()
            fragment.from = from
            Common.from = from
            if (pos != null) {
                fragment.pos = pos
            }
            return fragment
        }

        var mList: ArrayList<QuestionsModel> = ArrayList()
    }

    var from: Char? = null
    private var mAdapter: ReviewAnswerAdapter? = null
    var pos: Int = 0

    @SuppressLint("SetTextI18n")
    override fun initAction() {
        if (from == 'l') {
            binding.tvTitleCheckResult.text = "Lịch sử bài thi số ${pos + 1}"
            binding.ImgBackCheckResult.setOnClickListener {
                Common.replaceFragment(
                    requireActivity(),
                    R.id.FragmentLayout, FragmentExamHistory.newInstance()
                )
            }
            mList = ExamHistoryAdapter.getListQuestion() as ArrayList<QuestionsModel>
            mAdapter = ReviewAnswerAdapter(requireContext(), FragmentTakeExam.checkTrueFalse, 'l')
            mAdapter?.submitList(mList)
        } else {
            binding.tvTitleCheckResult.text = "Đáp án bài thi"
            binding.ImgBackCheckResult.setOnClickListener {
                Common.replaceFragment(
                    requireActivity(),
                    R.id.FragmentLayout, FragmentExamInformation.newInstance(Common.typeExam)
                )
            }
            mList.clear()
            for (i in 0 until SIZE) {
                mList.add(FragmentTakeExam.listData?.get(i) ?: return)
            }
            mAdapter = ReviewAnswerAdapter(requireContext(), FragmentTakeExam.checkTrueFalse, 't')
            mAdapter?.submitList(mList)
        }

        binding.RcvCheckResult.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.RcvCheckResult.adapter = mAdapter
    }

    override fun initData() {

    }
}