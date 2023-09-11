package com.example.drivingtest.adapter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.drivingtest.base.recycleView.BaseRecyclerView
import com.example.drivingtest.base.recycleView.BaseViewHolder
import com.example.drivingtest.databinding.ItemRcvHistoryBinding
import com.example.drivingtest.model.ExaminationModel
import com.example.drivingtest.model.QuestionModel
import com.example.drivingtest.ui.examination.FragmentExamination

class HistoryExamAdapter(val context: Context, mList: ArrayList<ExaminationModel>, val onClickItem: () -> Unit) :
    BaseRecyclerView<ExaminationModel, HistoryExamAdapter.ViewHolder>() {
    companion object {
        var pos: Int = -1
        var mListQuestions = ArrayList<ExaminationModel>()
        fun getListQuestion(): List<QuestionModel> {
            return mListQuestions[pos].listQuestion
        }
    }

    init {
        mListQuestions = mList
    }

    inner class ViewHolder(private val binding: ItemRcvHistoryBinding) :
        BaseViewHolder<ExaminationModel>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindViewHolder(data: ExaminationModel) {
            binding.btnExamHistory.text = "Đề ${position + 1}"
            binding.btnExamHistory.setOnClickListener {
                pos = position
                onClickItem.invoke()
            }
            FragmentExamination.SIZE = data.listQuestion.size
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(mList: ArrayList<ExaminationModel>) {
        /* */
    }

    override fun getListItem(): MutableList<ExaminationModel> = mListQuestions
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRcvHistoryBinding.inflate(
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