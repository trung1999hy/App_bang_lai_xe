package com.example.drivingtest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.drivingtest.base.recycleView.BaseRecyclerView
import com.example.drivingtest.base.recycleView.BaseViewHolder
import com.example.drivingtest.databinding.ItemRcvExamHistoryBinding
import com.example.drivingtest.model.TakeExamModel
import com.example.drivingtest.model.QuestionsModel
import com.example.drivingtest.ui.examination.FragmentTakeExam

class ExamHistoryAdapter(val context: Context, mList: ArrayList<TakeExamModel>, val onClickItem: () -> Unit) :
    BaseRecyclerView<TakeExamModel, ExamHistoryAdapter.ViewHolder>() {
    companion object {
        var pos: Int = -1
        var mListQuestions = ArrayList<TakeExamModel>()
        fun getListQuestion(): List<QuestionsModel> {
            return mListQuestions[pos].listQuestion
        }
    }

    init {
        mListQuestions = mList
    }

    inner class ViewHolder(private val binding: ItemRcvExamHistoryBinding) :
        BaseViewHolder<TakeExamModel>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindViewHolder(data: TakeExamModel) {
            binding.btnExamHistory.text = "Đề ${position + 1}"
            binding.btnExamHistory.setOnClickListener {
                pos = position
                onClickItem.invoke()
            }
            FragmentTakeExam.SIZE = data.listQuestion.size
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(mList: ArrayList<TakeExamModel>) {
        /* */
    }

    override fun getListItem(): MutableList<TakeExamModel> = mListQuestions
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRcvExamHistoryBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return mListQuestions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(mListQuestions[position])
    }
}