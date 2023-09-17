package com.example.drivingtest.ui.theory

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.drivingtest.R
import com.example.drivingtest.adapter.LearnTheoryAdapter
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentTheoryLearnBinding
import com.example.drivingtest.ui.home.FragmentHome
import com.example.drivingtest.utils.Common

class FragmentTheoryLearn : BaseFragmentWithBinding<FragmentTheoryLearnBinding>(
    FragmentTheoryLearnBinding::inflate
) {
    companion object {
        fun newInstance() = FragmentTheoryLearn()
    }

    private var countData = 1
    private var mAdapter: LearnTheoryAdapter? = null
    private val viewModel: FragmentTheoryLearnViewModel by viewModels()

    override fun initAction() {
        mAdapter = LearnTheoryAdapter(requireContext(), onClickItem = {})
        binding.RcvTheory.layoutManager =
            StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        binding.RcvTheory.adapter = mAdapter

        binding.BtnBack.setOnClickListener {
            if (countData != 1) {
                countData--
                getData(countData)
                binding.BtnNext.visibility = View.VISIBLE
                if (countData <= 1) binding.BtnBack.visibility = View.GONE
            }
        }

        binding.BtnNext.setOnClickListener {
            if (countData != 18) {
                countData++
                getData(countData)
                binding.BtnBack.visibility = View.VISIBLE
                if (countData >= 18) binding.BtnNext.visibility = View.GONE
            }
        }

        binding.ImgBackTheory.setOnClickListener {
            Common.replaceFragment(
                requireActivity(),
                R.id.FragmentLayout,
                FragmentHome.newInstance()
            )
        }
    }

    override fun initData() {
        getData(countData)
    }

    @SuppressLint("SetTextI18n")
    private fun getData(count: Int) {
        binding.toolbarTitle.text = "${count}/18"
        viewModel.getDataTheory(count, requireContext())
        viewModel.theory.observe(viewLifecycleOwner, Observer {
            mAdapter?.submitList(it)
        })
    }
}