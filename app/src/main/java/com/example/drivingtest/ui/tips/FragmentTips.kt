package com.example.drivingtest.ui.tips

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drivingtest.R
import com.example.drivingtest.adapter.TipsAdapter
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentTipsBinding
import com.example.drivingtest.inapp.PurchaseInAppActivity
import com.example.drivingtest.local.Preference
import com.example.drivingtest.local.tips.DatabaseTipsAccess
import com.example.drivingtest.model.TipsModel
import com.example.drivingtest.ui.home.FragmentHome
import com.example.drivingtest.utils.Common

class FragmentTips : BaseFragmentWithBinding<FragmentTipsBinding>(
    FragmentTipsBinding::inflate
) {
    companion object {
        fun newInstance() = FragmentTips()
    }

    private var mAdapter: TipsAdapter? = null
    private var indexItem: Int = 1

    @SuppressLint("SetTextI18n")
    override fun initAction() {
        mAdapter = TipsAdapter(requireContext(), onClick = {
            val preference = Preference.buildInstance(requireActivity())
            if ((preference?.getValueCoin() ?: 0) > 0) {
                val dialog = AlertDialog.Builder(requireActivity())
                dialog.setTitle("Thông báo")
                dialog.setMessage("Trừ 1 vàng để mở khoá")
                dialog.setNegativeButton(
                    "Cancle"
                ) { _, _ -> }

                dialog.setPositiveButton("OK") { _, _ ->
                    preference?.setValueCoin(preference.getValueCoin() - 1)
                    preference?.setKeyUnlock((preference.getKeyUnlock() as MutableSet<String>).apply {
                        add(it.id.toString())
                    })
                    unLockItem(indexItem)
                }
                dialog.show()
            } else {
                val alertDialog = AlertDialog.Builder(requireActivity())
                alertDialog.setTitle("You don't have enough gold ")
                    .setMessage("Open shop to buy more gold")
                    .setPositiveButton("Yes") { _, _ ->
                        val intent =
                            Intent(requireActivity(), PurchaseInAppActivity::class.java)
                        requireActivity().startActivity(intent)
                    }
                alertDialog.setNegativeButton("Cancel", null)
                alertDialog.show()
            }
        })
        mAdapter?.submitList(
            DatabaseTipsAccess.getInstance(requireContext())
                .getTheoreticalTips() as ArrayList<TipsModel>
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
                                .getTheoreticalTips() as ArrayList<TipsModel>
                        )
                        indexItem = 2
                    }

                    R.id.item_tip_signboard -> {
                        binding.tvTitle.text = "Các mẹo ghi nhớ biển báo"
                        mAdapter?.submitList(
                            DatabaseTipsAccess.getInstance(requireContext())
                                .getTipsNoticeBoard() as ArrayList<TipsModel>
                        )
                        indexItem = 3
                    }

                    else -> {
                        binding.tvTitle.text = "Các mẹo thi sa hình"
                        mAdapter?.submitList(
                            DatabaseTipsAccess.getInstance(requireContext())
                                .getTipsPicture() as ArrayList<TipsModel>
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

    private fun unLockItem(index: Int) {
        when (index) {
            1 -> {
                mAdapter?.submitList(
                    DatabaseTipsAccess.getInstance(requireContext())
                        .getTheoreticalTips() as ArrayList
                )
            }

            2 -> {
                mAdapter?.submitList(
                    DatabaseTipsAccess.getInstance(requireContext())
                        .getTheoreticalTips() as ArrayList<TipsModel>
                )
            }

            3 -> {
                mAdapter?.submitList(
                    DatabaseTipsAccess.getInstance(requireContext())
                        .getTipsNoticeBoard() as ArrayList<TipsModel>
                )
            }

            4 -> {
                mAdapter?.submitList(
                    DatabaseTipsAccess.getInstance(requireContext())
                        .getTipsPicture() as ArrayList<TipsModel>
                )
            }
        }
    }
}