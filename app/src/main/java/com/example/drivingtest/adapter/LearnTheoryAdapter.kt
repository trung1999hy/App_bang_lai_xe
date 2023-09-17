package com.example.drivingtest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.drivingtest.R
import com.example.drivingtest.base.recycleView.BaseRecyclerView
import com.example.drivingtest.base.recycleView.BaseViewHolder
import com.example.drivingtest.databinding.ItemRcvLearnTheoryBinding
import com.example.drivingtest.model.QuestionsModel

class LearnTheoryAdapter(
    val context: Context,
    val onClickItem: (QuestionsModel) -> Unit
) : BaseRecyclerView<QuestionsModel, LearnTheoryAdapter.ViewHolder>() {

    private var mList: MutableList<QuestionsModel> = ArrayList()

    inner class ViewHolder(private val binding: ItemRcvLearnTheoryBinding) :
        BaseViewHolder<QuestionsModel>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindViewHolder(data: QuestionsModel) {
            itemView.setOnClickListener { onClickItem.invoke(data) }

            binding.tvStt.text = data.id.toString()
            binding.tvQuestionTheory.text = data.questions
            binding.tvDapAn.text = "Đáp án: ${data.result}"

            binding.A.text = data.A
            binding.B.text = data.B
            if (data.image == 1) {
                binding.imageQuestionTheory.setImageDrawable(getDrawable(context, data))
                binding.imageQuestionTheory.visibility = View.VISIBLE
            } else {
                binding.imageQuestionTheory.visibility = View.GONE
            }
            if (data.C?.compareTo("") == 0) {
                binding.viewB.visibility = View.VISIBLE
                binding.viewB.visibility = View.GONE
            } else {
                binding.viewB2.visibility = View.GONE
                binding.viewB.visibility = View.VISIBLE
            }
            if (data.C?.compareTo("") != 0) {
                binding.C.visibility = View.VISIBLE
                binding.C.text = data.C
                binding.viewC.visibility = View.VISIBLE
                if (data.D?.compareTo("") == 0) {
                    binding.viewC2.visibility = View.VISIBLE
                    binding.viewC.visibility = View.GONE
                } else {
                    binding.viewC2.visibility = View.GONE
                    binding.viewC.visibility = View.VISIBLE
                }
            } else {
                binding.C.visibility = View.GONE
                binding.viewC.visibility = View.GONE
                binding.viewC2.visibility = View.GONE
            }
            if (data.D?.compareTo("") != 0) {
                binding.D.visibility = View.VISIBLE
                binding.D.text = data.D
                binding.viewD.visibility = View.VISIBLE
            } else {
                binding.D.visibility = View.GONE
                binding.viewD.visibility = View.GONE
            }
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables", "DiscouragedApi")
    fun getDrawable(
        context: Context,
        data: QuestionsModel
    ): Drawable? {
        val resources = context.resources
        val resourceId = resources.getIdentifier(
            "cauhoi" + data.id + "", "drawable",
            context.packageName
        )
        return resources.getDrawable(resourceId)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(mList: ArrayList<QuestionsModel>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun getListItem(): MutableList<QuestionsModel> = mList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRcvLearnTheoryBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return this.mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(mList[position])
        setAnimation(context, holder.itemView, R.anim.rcv_slide_in_left)
    }
}