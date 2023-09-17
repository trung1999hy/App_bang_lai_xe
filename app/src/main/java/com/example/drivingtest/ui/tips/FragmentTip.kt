package com.example.drivingtest.ui.tips

import android.annotation.SuppressLint
import android.view.Gravity
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drivingtest.R
import com.example.drivingtest.adapter.TipsAdapter
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentTipsBinding
import com.example.drivingtest.local.tips.DatabaseTipsAccess
import com.example.drivingtest.model.TipModel
import com.example.drivingtest.ui.home.FragmentHome
import com.example.drivingtest.utils.Common

class FragmentTip : BaseFragmentWithBinding<FragmentTipsBinding>(
    FragmentTipsBinding::inflate
) {
    companion object {
        fun newInstance() = FragmentTip()
    }

    private var mAdapter: TipsAdapter? = null
    private var indexItem: Int = 1

    @SuppressLint("SetTextI18n")
    override fun initAction() {
        mAdapter = TipsAdapter(requireContext(), onClick = {

        })
        mAdapter?.submitList(
            DatabaseTipsAccess.getInstance(requireContext())
                .getTheoreticalTips() as ArrayList<TipModel>
        )
        binding.RcvTips.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.RcvTips.adapter = mAdapter

        binding.btnMenu.setOnClickListener {
            val popMenu = PopupMenu(requireActivity(), binding.btnMenu)
            popMenu.menuInflater.inflate(R.menu.menu_tips, popMenu.menu)
            popMenu.setOnMenuItemClickListener { p0 ->
                when (p0?.itemId) {
                    R.id.item_tip_theory -> {
                        binding.tvTitle.text = "Các mẹo thi lý thuyết"
                        mAdapter?.submitList(
                            DatabaseTipsAccess.getInstance(requireContext())
                                .getTheoreticalTips() as ArrayList<TipModel>
                        )
                        indexItem = 2
                    }

                    R.id.item_tip_signboard -> {
                        binding.tvTitle.text = "Các mẹo ghi nhớ biển báo"
                        mAdapter?.submitList(
                            DatabaseTipsAccess.getInstance(requireContext())
                                .getTipsNoticeBoard() as ArrayList<TipModel>
                        )
                        indexItem = 3
                    }

                    else -> {
                        binding.tvTitle.text = "Các mẹo thi sa hình"
                        mAdapter?.submitList(
                            DatabaseTipsAccess.getInstance(requireContext())
                                .getTipsPicture() as ArrayList<TipModel>
                        )
                        indexItem = 4
                    }
                }
                true
            }

            popMenu.gravity = Gravity.CENTER_HORIZONTAL
            popMenu.show()
        }

        binding.imgBackTips.setOnClickListener {
            Common.replaceFragment(
                requireActivity(),
                R.id.FragmentLayout,
                FragmentHome.newInstance()
            )
        }
    }

    override fun initData() {

    }
}