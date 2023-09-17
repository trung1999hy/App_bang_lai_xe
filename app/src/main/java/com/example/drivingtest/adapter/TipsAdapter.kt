package com.example.drivingtest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.drivingtest.R
import com.example.drivingtest.base.recycleView.BaseRecyclerView
import com.example.drivingtest.base.recycleView.BaseViewHolder
import com.example.drivingtest.databinding.ItemRcvTipsBinding
import com.example.drivingtest.model.TipModel

class TipsAdapter(val context: Context, val onClick: (TipModel) -> Unit) :
    BaseRecyclerView<TipModel, TipsAdapter.ViewHolder>() {

     var mList: ArrayList<TipModel> = arrayListOf()

    inner class ViewHolder(val binding: ItemRcvTipsBinding) : BaseViewHolder<TipModel>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindViewHolder(data: TipModel) {
            binding.tvContent.text = "$position. ${data.content}"
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(mList: ArrayList<TipModel>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun getListItem(): MutableList<TipModel> = mList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRcvTipsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(mList[position])
        setAnimation(context, holder.itemView, R.anim.rcv_slide_in_left)
    }
}