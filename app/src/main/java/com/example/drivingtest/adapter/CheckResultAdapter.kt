package com.example.drivingtest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.drivingtest.base.recycleView.BaseRecyclerView
import com.example.drivingtest.base.recycleView.BaseViewHolder
import com.example.drivingtest.databinding.ItemRcvCheckResultBinding
import com.example.drivingtest.model.QuestionModel

class CheckResultAdapter(val context: Context, val checkTrueFalse: BooleanArray, val t: Char) :
    BaseRecyclerView<QuestionModel, CheckResultAdapter.ViewHolder>() {

    private var mList: ArrayList<QuestionModel> = ArrayList()

    inner class ViewHolder(private val binding: ItemRcvCheckResultBinding) :
        BaseViewHolder<QuestionModel>(binding) {
        @SuppressLint("SetTextI18n")
        override fun bindViewHolder(data: QuestionModel) {
            binding.tvStt.text = "${position + 1}"
            binding.tvQuestion.text = mList[position].questions
            if (mList[position].image == 1) {
                binding.imgQuestion.setImageDrawable(getDrawable(context, position))
                binding.imgQuestion.visibility = View.VISIBLE
            } else binding.imgQuestion.visibility = View.GONE
            binding.A.text = mList[position].A
            if (mList[position].result?.contains("1")!!
            ) binding.A.setTextColor(Color.parseColor("#4CAF50")) else binding.A.setTextColor(
                Color.BLACK
            )
            binding.B.text = mList[position].B
            if (mList[position].result?.contains("2")!!
            ) binding.B.setTextColor(Color.parseColor("#4CAF50")) else binding.B.setTextColor(
                Color.BLACK
            )
            if (mList[position].C?.compareTo("") == 0) {
                binding.viewB2.visibility = View.VISIBLE
                binding.viewB.visibility = View.GONE
            } else {
                binding.viewB2.visibility = View.GONE
                binding.viewB.visibility = View.VISIBLE
            }
            if (mList[position].C?.compareTo("") != 0) {
                binding.C.visibility = View.VISIBLE
                binding.C.text = mList[position].C
                if (mList[position].result?.contains("3") == true
                ) binding.C.setTextColor(Color.parseColor("#4CAF50")) else binding.C.setTextColor(
                    Color.BLACK
                )
                binding.viewC.visibility = View.VISIBLE
                if (mList[position].D?.compareTo("") == 0) {
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
            if (mList[position].D?.compareTo("") != 0) {
                binding.D.visibility = View.VISIBLE
                binding.D.text = mList[position].D
                if (mList[position].result?.contains("4") == true
                ) binding.D.setTextColor(Color.parseColor("#4CAF50")) else binding.D.setTextColor(
                    Color.BLACK
                )
                binding.viewD.visibility = View.VISIBLE
            } else {
                binding.D.visibility = View.GONE
                binding.viewD.visibility = View.GONE
            }
            if (checkTrueFalse[position]) binding.imgTrueFalse.setImageDrawable(
                getDrawableByName(
                    context,
                    "ic_true"
                )
            ) else binding.imgTrueFalse.setImageDrawable(getDrawableByName(context, "ic_false"))
            if (!mList[position].result?.contains("1")!! && mList[position]
                    .lcA == 1
            ) binding.A.setTextColor(
                Color.RED
            )
            if (!mList[position].result?.contains("2")!! && mList[position]
                    .lcB == 1
            ) binding.B.setTextColor(
                Color.RED
            )
            if (!mList[position].result?.contains("3")!! && mList[position]
                    .lcC == 1
            ) binding.C.setTextColor(
                Color.RED
            )
            if (!mList[position].result?.contains("4")!! && mList[position]
                    .lcD == 1
            ) binding.D.setTextColor(
                Color.RED
            )
            if (t == 't' || t == 'l') {
                binding.imgTrueFalse.visibility = View.VISIBLE
            } else {
                binding.imgTrueFalse.visibility = View.GONE
            }
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun submitList(mList: ArrayList<QuestionModel>) {
        this.mList = mList
        notifyDataSetChanged()
    }

    override fun getListItem(): MutableList<QuestionModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRcvCheckResultBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(mList[position])
    }

    @SuppressLint("DiscouragedApi", "UseCompatLoadingForDrawables")
    fun getDrawable(
        context: Context,
        position: Int
    ): Drawable? {
        val resources = context.resources
        val resourceId = resources.getIdentifier(
            "cauhoi" + mList[position].id + "", "drawable",
            context.packageName
        )
        return resources.getDrawable(resourceId)
    }

    @SuppressLint("DiscouragedApi", "UseCompatLoadingForDrawables")
    fun getDrawableByName(
        context: Context,
        name: String?
    ): Drawable? {
        val resources = context.resources
        val resourceId = resources.getIdentifier(
            name, "drawable",
            context.packageName
        )
        return resources.getDrawable(resourceId)
    }
}