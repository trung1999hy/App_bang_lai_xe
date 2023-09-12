package com.example.drivingtest.ui.examination.result.checkresult

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drivingtest.R
import com.example.drivingtest.adapter.CheckResultAdapter
import com.example.drivingtest.adapter.HistoryExamAdapter
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentCheckResultBinding
import com.example.drivingtest.model.QuestionModel
import com.example.drivingtest.ui.examination.FragmentExamination
import com.example.drivingtest.ui.examination.FragmentExamination.Companion.SIZE
import com.example.drivingtest.ui.examination.noti.FragmentNotiExam
import com.example.drivingtest.ui.history.FragmentHistoryExam
import com.example.drivingtest.utils.Common

class FragmentCheckResult : BaseFragmentWithBinding<FragmentCheckResultBinding>(
    FragmentCheckResultBinding::inflate
) {
    companion object {
        fun newInstance(from: Char? = null, pos: Int ?= null): FragmentCheckResult {
            val fragment = FragmentCheckResult()
            fragment.from = from
            Common.from = from
            if (pos != null) {
                fragment.pos = pos
            }
            return fragment
        }

        var mList: ArrayList<QuestionModel> = ArrayList()
    }

    var from: Char? = null
    private var mAdapter: CheckResultAdapter? = null
    var pos: Int = 0

    @SuppressLint("SetTextI18n")
    override fun initAction() {
        if (from == 'l') {
            binding.tvTitleCheckResult.text = "Lịch sử bài thi số ${pos + 1}"
            binding.ImgBackCheckResult.setOnClickListener {
                Common.replaceFragment(
                    requireActivity(),
                    R.id.FragmentLayout, FragmentHistoryExam.newInstance()
                )
            }
            mList = HistoryExamAdapter.getListQuestion() as ArrayList<QuestionModel>
            mAdapter = CheckResultAdapter(requireContext(), FragmentExamination.checkTrueFalse, 'l')
            mAdapter?.submitList(mList)
        } else {
            binding.tvTitleCheckResult.text = "Đáp án bài thi"
            binding.ImgBackCheckResult.setOnClickListener {
                Common.replaceFragment(
                    requireActivity(),
                    R.id.FragmentLayout, FragmentNotiExam.newInstance(Common.typeExam)
                )
            }
            mList.clear()
            for (i in 0 until SIZE) {
                mList.add(FragmentExamination.listData?.get(i) ?: return)
            }
            mAdapter = CheckResultAdapter(requireContext(), FragmentExamination.checkTrueFalse, 't')
            mAdapter?.submitList(mList)
        }

        binding.RcvCheckResult.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.RcvCheckResult.adapter = mAdapter
    }

    override fun initData() {

    }
}