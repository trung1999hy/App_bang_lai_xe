package com.example.drivingtest.ui.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.drivingtest.R
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.base.swipe.OnSwipeTouchListener
import com.example.drivingtest.databinding.FragmentHomeBinding
import com.example.drivingtest.inapp.PurchaseInAppActivity
import com.example.drivingtest.local.Preference
import com.example.drivingtest.ui.SplashActivity
import com.example.drivingtest.ui.examination.noti.FragmentNotiExam
import com.example.drivingtest.ui.history.FragmentHistoryExam
import com.example.drivingtest.ui.signboard.FragmentSignBoard
import com.example.drivingtest.ui.theory.FragmentTheory
import com.example.drivingtest.ui.tips.FragmentTips
import com.example.drivingtest.ui.tippractice.FragmentTipsPractice
import com.example.drivingtest.utils.Common

class FragmentHome : BaseFragmentWithBinding<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
), View.OnClickListener {
    companion object {
        fun newInstance() = FragmentHome()
    }

    private val viewModel: FragmentHomeViewModel by viewModels()
    private var typeExam: String = "A1,A2"

    private lateinit var dialogTipPractice: Dialog

    override fun initAction() {
        configDialogTipsPra()
        val adapter = SpinnerTypeAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            viewModel.typeDriverList.value ?: return
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.SpinnerType.adapter = adapter

        binding.SpinnerType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                binding.ToolBarHome.title = "Ôn thi ${viewModel.typeDriverList.value!![position]}"
                typeExam = when (position) {
                    0, 1 -> {
                        "A1,A2"
                    }

                    2 -> {
                        "B1"
                    }

                    else -> {
                        "B2"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.LearnTheory.setOnClickListener {
            replaceFragmentToMain(FragmentTheory.newInstance())
        }

        binding.Examination.setOnClickListener {
            replaceFragmentToMain(FragmentNotiExam.newInstance(typeExam = typeExam))
        }

        binding.TipsPractice.setOnClickListener {
            dialogTipPractice.show()
        }

        binding.SignBoard.setOnClickListener {
            replaceFragmentToMain(FragmentSignBoard.newInstance())
        }

        binding.Tips.setOnClickListener {
            replaceFragmentToMain(FragmentTips.newInstance())
        }

        binding.HistoryExam.setOnClickListener {
            replaceFragmentToMain(FragmentHistoryExam.newInstance())
        }

        binding.tvSwipe.setOnTouchListener(object : OnSwipeTouchListener(requireActivity()) {
            override fun onSwipeLeft() {
                Common.openActivity(requireActivity(), PurchaseInAppActivity::class.java)
            }
        })

        val preference = Preference.buildInstance(requireContext())
        binding.coin.text = preference?.getValueCoin().toString()
    }

    private fun replaceFragmentToMain(fragment: Fragment) {
        Common.replaceFragment(requireActivity(), R.id.FragmentLayout, fragment)
    }

    private fun replaceFragmentTipsPra(type: String) {
        replaceFragmentToMain(FragmentTipsPractice.newInstance(typeTipsPractice = type))
        dialogTipPractice.dismiss()
    }

    override fun initData() {

    }

    private fun configDialogTipsPra() {
        dialogTipPractice = Dialog(requireContext())
        dialogTipPractice.setContentView(R.layout.custom_dialog_tips_practice)
        dialogTipPractice.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogTipPractice.setCanceledOnTouchOutside(true)
        dialogTipPractice.findViewById<Button>(R.id.btnA1A2).setOnClickListener(this)
        dialogTipPractice.findViewById<Button>(R.id.btnB1).setOnClickListener(this)
        dialogTipPractice.findViewById<Button>(R.id.btnCancel).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnA1A2 -> {
                replaceFragmentTipsPra("A1,A2")
            }

            R.id.btnB1 -> {
                replaceFragmentTipsPra("B1,B2")
            }

            else -> {
                dialogTipPractice.dismiss()
            }
        }
    }
}