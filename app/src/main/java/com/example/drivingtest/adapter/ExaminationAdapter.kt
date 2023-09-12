package com.example.drivingtest.adapter

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.example.drivingtest.base.recycleView.BaseRecyclerView
import com.example.drivingtest.base.recycleView.BaseViewHolder
import com.example.drivingtest.databinding.ItemRcvExaminationBinding
import com.example.drivingtest.model.QuestionModel
import com.example.drivingtest.ui.examination.FragmentExamination

class ExaminationAdapter(val context: Context, var _data: QuestionModel, var pos: Int, var size: Int) :
    BaseRecyclerView<QuestionModel, ExaminationAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRcvExaminationBinding) :
        BaseViewHolder<QuestionModel>(binding), CompoundButton.OnCheckedChangeListener {
        @SuppressLint("SetTextI18n")
        override fun bindViewHolder(data: QuestionModel) {
            binding.tvStt.text = "${pos + 1}/$size"
            binding.tvQuestionExam.text = data.questions
            binding.A.text = data.A
            binding.B.text = data.B
            if (data.image == 1) {
                binding.imageQuestions.setImageDrawable(getDrawable(context, data))
                binding.imageQuestions.visibility = View.VISIBLE
            } else {
                binding.imageQuestions.visibility = View.GONE
            }
            if (data.C?.compareTo("") == 0) {
                binding.viewB2.visibility = View.VISIBLE
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

            binding.A.isChecked = data.lcA != 0
            binding.B.isChecked = data.lcB != 0
            binding.C.isChecked = data.lcC != 0
            binding.D.isChecked = data.lcD != 0
            binding.A.setOnCheckedChangeListener(this)
            binding.B.setOnCheckedChangeListener(this)
            binding.C.setOnCheckedChangeListener(this)
            binding.D.setOnCheckedChangeListener(this)
        }

        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
            if (binding.A.isChecked) _data.lcA = 1 else _data.lcA = 0
            if (binding.B.isChecked) _data.lcB = 1 else _data.lcB = 0
            if (binding.C.isChecked) _data.lcC = 1 else _data.lcC = 0
            if (binding.D.isChecked) _data.lcD = 1 else _data.lcD = 0
            FragmentExamination.listData?.set(pos, _data)
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

    fun getDA(): StringBuilder {
        val sb = StringBuilder()
        val sb1 = StringBuilder()
        if (_data.lcA == 1) sb.append("1")
        if (_data.lcB == 1) sb.append("2")
        if (_data.lcC == 1) sb.append("3")
        if (_data.lcD == 1) sb.append("4")
        for (element in sb) {
            sb1.append("$element-")
            Log.d(TAG, "getDA: $element")
        }
        if (sb1.isNotEmpty()) sb1.deleteCharAt(sb1.length - 1)
        return sb1
    }

    override fun submitList(mList: ArrayList<QuestionModel>) {
        /* */
    }

    override fun getListItem(): MutableList<QuestionModel>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRcvExaminationBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(_data)
    }
}