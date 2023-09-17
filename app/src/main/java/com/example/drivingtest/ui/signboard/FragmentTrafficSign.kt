package com.example.drivingtest.ui.signboard

import android.annotation.SuppressLint
import android.view.Gravity
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drivingtest.R
import com.example.drivingtest.adapter.TrafficSignAdapter
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentTrafficSignBinding
import com.example.drivingtest.local.noticeboard.DatabaseNoticeBoardAccess
import com.example.drivingtest.model.TrafficSignModel
import com.example.drivingtest.ui.home.FragmentHome
import com.example.drivingtest.utils.Common

class FragmentTrafficSign : BaseFragmentWithBinding<FragmentTrafficSignBinding>(
    FragmentTrafficSignBinding::inflate
) {
    companion object {
        fun newInstance() = FragmentTrafficSign()
    }

    private var mAdapter: TrafficSignAdapter? = null

    @SuppressLint("SetTextI18n")
    override fun initAction() {
        mAdapter = TrafficSignAdapter(requireContext())
        mAdapter?.submitList(
            DatabaseNoticeBoardAccess.getInstance(requireContext())
                .getListDangerSign() as ArrayList<TrafficSignModel>
        )
        binding.RcvSignBoard.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.RcvSignBoard.adapter = mAdapter

        binding.btnMenuBoard.setOnClickListener {
            val popMenu = PopupMenu(requireActivity(), binding.btnMenuBoard)
            popMenu.menuInflater.inflate(R.menu.menu_signboard, popMenu.menu)
            popMenu.setOnMenuItemClickListener { p0 ->
                when (p0?.itemId) {
                    R.id.item_board_danger -> {
                        binding.tvTitle.text = "Biển báo nguy hiểm"
                        mAdapter?.submitList(
                            DatabaseNoticeBoardAccess.getInstance(requireContext())
                                .getListDangerSign() as ArrayList<TrafficSignModel>
                        )
                    }

                    R.id.item_board_forbidden -> {
                        binding.tvTitle.text = "Biển báo cấm"
                        mAdapter?.submitList(
                            DatabaseNoticeBoardAccess.getInstance(requireContext())
                                .getListForbiddenSign() as ArrayList<TrafficSignModel>
                        )
                    }

                    R.id.item_board_command -> {
                        binding.tvTitle.text = "Biển báo hiệu lệnh"
                        mAdapter?.submitList(
                            DatabaseNoticeBoardAccess.getInstance(requireContext())
                                .getListCommandSignboard() as ArrayList<TrafficSignModel>
                        )
                    }

                    R.id.item_board_directional -> {
                        binding.tvTitle.text = "Biển báo chỉ dẫn"
                        mAdapter?.submitList(
                            DatabaseNoticeBoardAccess.getInstance(requireContext())
                                .getListDirectionalSignboard() as ArrayList<TrafficSignModel>
                        )
                    }

                    else -> {
                        binding.tvTitle.text = "Biển báo phụ"
                        mAdapter?.submitList(
                            DatabaseNoticeBoardAccess.getInstance(requireContext())
                                .getListAuxiliarySignboard() as ArrayList<TrafficSignModel>
                        )
                    }
                }
                true
            }

            popMenu.gravity = Gravity.CENTER_HORIZONTAL
            popMenu.show()
        }

        binding.ImgBackSignBoard.setOnClickListener {
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