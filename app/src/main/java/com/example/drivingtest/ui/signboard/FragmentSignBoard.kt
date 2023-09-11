package com.example.drivingtest.ui.signboard

import android.annotation.SuppressLint
import android.view.Gravity
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drivingtest.R
import com.example.drivingtest.adapter.SignBoardAdapter
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentSignboardBinding
import com.example.drivingtest.local.noticeboard.DatabaseNoticeBoardAccess
import com.example.drivingtest.model.NoticeBoardModel
import com.example.drivingtest.ui.home.FragmentHome
import com.example.drivingtest.utils.Common

class FragmentSignBoard : BaseFragmentWithBinding<FragmentSignboardBinding>(
    FragmentSignboardBinding::inflate
) {
    companion object {
        fun newInstance() = FragmentSignBoard()
    }

    private var mAdapter: SignBoardAdapter? = null

    @SuppressLint("SetTextI18n")
    override fun initAction() {
        mAdapter = SignBoardAdapter(requireContext())
        mAdapter?.submitList(
            DatabaseNoticeBoardAccess.getInstance(requireContext())
                .getListNoticeBoard() as ArrayList<NoticeBoardModel>
        )
        binding.RcvSignBoard.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.RcvSignBoard.adapter = mAdapter

        binding.btnMenuBoard.setOnClickListener {
            val popMenu = PopupMenu(requireActivity(), binding.btnMenuBoard)
            popMenu.menuInflater.inflate(R.menu.menu_signboard, popMenu.menu)
            popMenu.setOnMenuItemClickListener { p0 ->
                when (p0?.itemId) {
                    R.id.item_board_all -> {
                        binding.tvTitle.text = "Tất cả biển báo"
                        mAdapter?.submitList(
                            DatabaseNoticeBoardAccess.getInstance(requireContext())
                                .getListNoticeBoard() as ArrayList<NoticeBoardModel>
                        )
                    }

                    R.id.item_board_danger -> {
                        binding.tvTitle.text = "Biển báo nguy hiểm"
                        mAdapter?.submitList(
                            DatabaseNoticeBoardAccess.getInstance(requireContext())
                                .getListDangerSign() as ArrayList<NoticeBoardModel>
                        )
                    }

                    R.id.item_board_forbidden -> {
                        binding.tvTitle.text = "Biển báo cấm"
                        mAdapter?.submitList(
                            DatabaseNoticeBoardAccess.getInstance(requireContext())
                                .getListForbiddenSign() as ArrayList<NoticeBoardModel>
                        )
                    }

                    R.id.item_board_command -> {
                        binding.tvTitle.text = "Biển báo hiệu lệnh"
                        mAdapter?.submitList(
                            DatabaseNoticeBoardAccess.getInstance(requireContext())
                                .getListCommandSignboard() as ArrayList<NoticeBoardModel>
                        )
                    }

                    R.id.item_board_directional -> {
                        binding.tvTitle.text = "Biển báo chỉ dẫn"
                        mAdapter?.submitList(
                            DatabaseNoticeBoardAccess.getInstance(requireContext())
                                .getListDirectionalSignboard() as ArrayList<NoticeBoardModel>
                        )
                    }

                    else -> {
                        binding.tvTitle.text = "Biển báo phụ"
                        mAdapter?.submitList(
                            DatabaseNoticeBoardAccess.getInstance(requireContext())
                                .getListAuxiliarySignboard() as ArrayList<NoticeBoardModel>
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