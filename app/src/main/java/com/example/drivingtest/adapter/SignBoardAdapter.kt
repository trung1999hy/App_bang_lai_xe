package com.example.drivingtest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.drivingtest.base.recycleView.BaseRecyclerView
import com.example.drivingtest.base.recycleView.BaseViewHolder
import com.example.drivingtest.databinding.ItemRcvSignboardBinding
import com.example.drivingtest.model.NoticeBoardModel

class SignBoardAdapter(val context: Context) : BaseRecyclerView<NoticeBoardModel, SignBoardAdapter.ViewHolder>() {

    private var mList : ArrayList<NoticeBoardModel> = arrayListOf()

    inner class ViewHolder(private val binding: ItemRcvSignboardBinding) : BaseViewHolder<NoticeBoardModel>(binding) {
        override fun bindViewHolder(data: NoticeBoardModel) {
            binding.tvContentBoard.text = data.content
            binding.imageSignBoard.setImageDrawable(getDrawable(context, data))
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(mList: ArrayList<NoticeBoardModel>) {
       this.mList = mList
        notifyDataSetChanged()
    }

    override fun getListItem(): MutableList<NoticeBoardModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ItemRcvSignboardBinding.inflate(LayoutInflater.from(context), parent, false))
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
        data: NoticeBoardModel
    ): Drawable? {
        val resources = context.resources
        val resourceId = resources.getIdentifier(
            "bienbao" + data.image, "drawable",
            context.packageName
        )
        return resources.getDrawable(resourceId)
    }
}