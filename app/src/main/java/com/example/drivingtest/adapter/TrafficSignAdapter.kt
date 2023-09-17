package com.example.drivingtest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.drivingtest.base.recycleView.BaseRecyclerView
import com.example.drivingtest.base.recycleView.BaseViewHolder
import com.example.drivingtest.databinding.ItemRcvTrafficSignBinding
import com.example.drivingtest.model.TrafficSignModel

class TrafficSignAdapter(val context: Context) : BaseRecyclerView<TrafficSignModel, TrafficSignAdapter.ViewHolder>() {

    private var mList : ArrayList<TrafficSignModel> = arrayListOf()

    inner class ViewHolder(private val binding: ItemRcvTrafficSignBinding) : BaseViewHolder<TrafficSignModel>(binding) {
        override fun bindViewHolder(data: TrafficSignModel) {
            binding.tvContentBoard.text = data.content
            binding.imageSignBoard.setImageDrawable(getDrawable(context, data))
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(mList: ArrayList<TrafficSignModel>) {
       this.mList = mList
        notifyDataSetChanged()
    }

    override fun getListItem(): MutableList<TrafficSignModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ItemRcvTrafficSignBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
       return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bindViewHolder(mList[position])
    }

    @SuppressLint("UseCompatLoadingForDrawables", "DiscouragedApi")
    fun getDrawable(
        context: Context,
        data: TrafficSignModel
    ): Drawable? {
        val resources = context.resources
        val resourceId = resources.getIdentifier(
            "bienbao" + data.image, "drawable",
            context.packageName
        )
        return resources.getDrawable(resourceId)
    }
}