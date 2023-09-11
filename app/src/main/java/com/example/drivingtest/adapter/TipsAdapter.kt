package com.example.drivingtest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.drivingtest.base.recycleView.BaseRecyclerView
import com.example.drivingtest.base.recycleView.BaseViewHolder
import com.example.drivingtest.databinding.ItemRcvTipsBinding
import com.example.drivingtest.model.TipsModel

class TipsAdapter(val context: Context, val onClick: (TipsModel) -> Unit) :
    BaseRecyclerView<TipsModel, TipsAdapter.ViewHolder>() {

     var mList: ArrayList<TipsModel> = arrayListOf()

    inner class ViewHolder(val binding: ItemRcvTipsBinding) : BaseViewHolder<TipsModel>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindViewHolder(data: TipsModel) {
            itemView.setOnClickListener {
                if(data.lock) onClick.invoke(data)
            }
            if(!data.lock) {
                binding.lockContent.visibility = View.GONE
                binding.tvContent.visibility = View.VISIBLE
            } else {
                binding.lockContent.visibility = View.VISIBLE
                binding.tvContent.visibility = View.GONE
            }
            binding.tvContent.text = "$position. ${data.content}"
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(mList: ArrayList<TipsModel>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun getListItem(): MutableList<TipsModel>? = mList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRcvTipsBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(mList[position])
    }
}