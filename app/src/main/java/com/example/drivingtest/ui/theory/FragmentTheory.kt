package com.example.drivingtest.ui.theory

import android.content.ContentValues.TAG
import android.util.Log
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentTheoryBinding
import com.example.drivingtest.local.questions.DatabaseQuestionAccess

class FragmentTheory : BaseFragmentWithBinding<FragmentTheoryBinding>(
    FragmentTheoryBinding::inflate
) {
    companion object {
        fun newInstance() = FragmentTheory()
    }

    override fun initAction() {

    }

    override fun initData() {
        for(x in DatabaseQuestionAccess.getInstance(requireContext()).getQuestionsTheory(1)) {
            Log.d(TAG, "initData: ${x.result}")
        }
    }
}