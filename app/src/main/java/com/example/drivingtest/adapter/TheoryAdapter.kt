package com.example.drivingtest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.drivingtest.base.recycleView.BaseRecyclerView
import com.example.drivingtest.base.recycleView.BaseViewHolder
import com.example.drivingtest.databinding.ItemRcvTheoryBinding
import com.example.drivingtest.model.QuestionModel

class TheoryAdapter(
    val context: Context,
    val onClickItem: (QuestionModel) -> Unit
) : BaseRecyclerView<QuestionModel, TheoryAdapter.ViewHolder>() {

    private var mList: MutableList<QuestionModel> = ArrayList()

    inner class ViewHolder(private val binding: ItemRcvTheoryBinding) :
        BaseViewHolder<QuestionModel>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindViewHolder(data: QuestionModel) {
            itemView.setOnClickListener { onClickItem.invoke(data) }

            binding.tvStt.text = data.id.toString()
            binding.tvCauHoi.text = data.questions
            binding.tvDapAn.text = "Đáp án: ${data.result}"

            binding.A.text = data.A
            binding.B.text = data.B
            if (data.image == 1) {
                binding.anhCauHoi.setImageDrawable(getDrawable(context, data))
                binding.anhCauHoi.visibility = View.VISIBLE
            } else {
                binding.anhCauHoi.visibility = View.GONE
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
        data: QuestionModel
    ): Drawable? {
        val resources = context.resources
        val resourceId = resources.getIdentifier(
            "cauhoi" + data.id + "", "drawable",
            context.packageName
        )
        return resources.getDrawable(resourceId)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(mList: ArrayList<QuestionModel>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun getListItem(): MutableList<QuestionModel> = mList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRcvTheoryBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return this.mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(mList[position])
    }
}