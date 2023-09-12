package com.example.drivingtest.ui.history

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.drivingtest.R
import com.example.drivingtest.adapter.HistoryExamAdapter
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentHistoryExamBinding
import com.example.drivingtest.model.ExaminationModel
import com.example.drivingtest.ui.examination.result.checkresult.FragmentCheckResult
import com.example.drivingtest.ui.home.FragmentHome
import com.example.drivingtest.utils.Common
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.ObjectInputStream

@Suppress("UNCHECKED_CAST")
class FragmentHistoryExam : BaseFragmentWithBinding<FragmentHistoryExamBinding>(
    FragmentHistoryExamBinding::inflate
) {
    companion object {
        fun newInstance() = FragmentHistoryExam()
    }

    private var mAdapter: HistoryExamAdapter? = null

    private var mList: ArrayList<ExaminationModel>? = arrayListOf()

    override fun initAction() {
        mList?.addAll(readFile("historyExam.txt"))
        mAdapter = HistoryExamAdapter(requireContext(), mList ?: return, onClickItem = {
            Common.replaceFragment(
                requireActivity(),
                R.id.FragmentLayout,
                FragmentCheckResult.newInstance('l', HistoryExamAdapter.pos)
            )
        })
        binding.RcvHistoryExam.layoutManager =
            StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        binding.RcvHistoryExam.adapter = mAdapter

        binding.imgBackHis.setOnClickListener {
            Common.replaceFragment(
                requireActivity(),
                R.id.FragmentLayout,
                FragmentHome.newInstance()
            )
        }
    }

    override fun initData() {

    }

    private fun readFile(fileName: String?): ArrayList<ExaminationModel> {
        var listExam: ArrayList<ExaminationModel> = ArrayList()
        try {
            var file: File = requireActivity().getFileStreamPath(fileName)
            if (!file.exists()) {
                file = File(fileName.toString())
            }
            val fis = FileInputStream(file)
            val ois = ObjectInputStream(fis)
            listExam = ois.readObject() as ArrayList<ExaminationModel>
            ois.close()
            fis.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return listExam
    }
}