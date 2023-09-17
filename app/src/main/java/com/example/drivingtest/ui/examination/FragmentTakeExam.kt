package com.example.drivingtest.ui.examination

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drivingtest.R
import com.example.drivingtest.adapter.TakeExamAdapter
import com.example.drivingtest.base.BaseFragmentWithBinding
import com.example.drivingtest.databinding.FragmentTakeExamBinding
import com.example.drivingtest.local.questions.DatabaseQuestionAccess
import com.example.drivingtest.model.TakeExamModel
import com.example.drivingtest.model.QuestionsModel
import com.example.drivingtest.ui.examination.result.FragmentResults
import com.example.drivingtest.utils.Common
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.Random

@Suppress("UNCHECKED_CAST")
class FragmentTakeExam : BaseFragmentWithBinding<FragmentTakeExamBinding>(
    FragmentTakeExamBinding::inflate
), View.OnClickListener {
    companion object {
        fun newInstance(typeExam: String): FragmentTakeExam {
            val fragment = FragmentTakeExam()
            fragment.typeExam = typeExam
            return fragment
        }

        var SIZE: Int = 0
        var listData: ArrayList<QuestionsModel>? = null
        var numberCorrect = 0
        var checkTrueFalse = BooleanArray(100)
        var sttQuestions = IntArray(100)
    }

    private var listQuestion = arrayListOf<QuestionsModel>()
    private var listExam = arrayListOf<TakeExamModel>()
    private var FLAG = 0
    private val answerChoice: Array<StringBuilder?> = arrayOfNulls(35)
    private var time = 1200
    private var dialogFinish: Dialog? = null
    private var dialogTimeOut: Dialog? = null
    private var mAdapter: TakeExamAdapter? = null

    private var btnDestroy: Button? = null
    private var btnOk: Button? = null
    private var btnCancel: Button? = null

    private var count: Int = 1
    private var isCheckOk: Boolean = false

    var typeExam: String? = null

    val t = Thread {
        while (time > 0 && FLAG == 1) {
            time--
            if (isAdded) {
                (activity as AppCompatActivity).runOnUiThread(Runnable {
                    setTime()
                    if (time == 0) {
                        isCheckOk = true
                        dialogTimeOut?.show()
                    }
                })
            }
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }


    override fun initAction() {
        SIZE = if (typeExam.equals("A1,A2")) 25 else if (typeExam.equals("B1")) 30 else 35
        randomQuestion()
        setControl()
        listExam = readFile("historyExam.txt") as ArrayList<TakeExamModel>
        if (listExam.size < 25) {
            listExam.add(TakeExamModel(listData ?: return))
        } else {
            for (i in 24 downTo 1) listExam[i] = listExam[i - 1]
            listExam[0] = TakeExamModel(listData ?: return)
        }
        FLAG = 1
        time = if(typeExam.equals("B2")) 1300 else 1200
        t.start()
    }

    override fun initData() {

    }

    private fun setControl() {
        setTime()
        for (i in 0 until SIZE) answerChoice[i] = StringBuilder()
        configDialog()

        btnDestroy?.setOnClickListener(this)
        btnOk?.setOnClickListener(this)
        binding.BtnBackE.setOnClickListener(this)
        binding.BtnNextE.setOnClickListener(this)
        btnCancel?.setOnClickListener(this)
        binding.tvDone.setOnClickListener(this)

        mAdapter = TakeExamAdapter(requireContext(), listData?.get(0) ?: return, 0, SIZE)
        binding.RcvExamination.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.RcvExamination.adapter = mAdapter
    }

    private fun configDialog() {
        dialogFinish = Dialog(requireContext())
        dialogFinish?.setContentView(R.layout.dialog_finish)
        dialogFinish?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogFinish?.setCanceledOnTouchOutside(false)
        dialogTimeOut = Dialog(requireContext())
        dialogTimeOut?.setContentView(R.layout.dialog_timeout)
        dialogTimeOut?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogTimeOut?.setCanceledOnTouchOutside(false)
        btnDestroy = dialogFinish?.findViewById(R.id.bt_huyBo)
        btnOk = dialogFinish?.findViewById(R.id.bt_dongY)
        btnCancel = dialogTimeOut?.findViewById(R.id.bt_cancel)
    }

    @SuppressLint("SetTextI18n")
    fun setTime() {
        val minute = time / 60
        val second = time % 60
        binding.tvTime.text = String.format("%02d:%02d", minute, second)
    }

    private fun randomQuestion() {
        listData = arrayListOf()
        val a = arrayListOf<Int>()
        val rd = Random()
        listQuestion = DatabaseQuestionAccess.getInstance(requireContext())
            .getQuestions() as ArrayList<QuestionsModel>
        var x: Int
        for (i in 0 until SIZE) {
            do {
                val j: Int = 450 - SIZE + i
                x = rd.nextInt(j)
            } while (a.contains(x))
            a.add(x)
            listData?.add(listQuestion[a[i]])
        }
    }

    private fun readFile(fileName: String?): List<TakeExamModel?> {
        var listExam: List<TakeExamModel?> = ArrayList()
        try {
            var file: File = requireContext().getFileStreamPath(fileName)
            if (!file.exists()) {
                file = File(fileName.toString())
            }
            val fis = FileInputStream(file)
            val ois = ObjectInputStream(fis)
            listExam = ois.readObject() as List<TakeExamModel?>
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

    private fun writeFile(listExam: List<TakeExamModel?>?) {
        try {
            val file: File = requireContext().getFileStreamPath("historyExam.txt")
            val fos = FileOutputStream(file)
            val oos = ObjectOutputStream(fos)
            oos.writeObject(listExam)
            oos.close()
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun resetQuestions() {
        mAdapter = TakeExamAdapter(
            requireContext(),
            listData?.get(count - 1) ?: return,
            count - 1,
            SIZE
        )
        binding.RcvExamination.adapter = mAdapter
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.BtnBackE -> {
                answerChoice[count - 1] = mAdapter?.getDA()
                if (count != 1) {
                    binding.BtnNextE.visibility = View.VISIBLE
                    count--
                    resetQuestions()
                    if (count <= 1) {
                        binding.BtnBackE.visibility = View.GONE
                    }
                }
            }

            R.id.BtnNextE -> {
                answerChoice[count - 1] = mAdapter?.getDA()
                if (count != SIZE) {
                    binding.BtnBackE.visibility = View.VISIBLE
                    count++
                    resetQuestions()
                    if (count >= SIZE) {
                        binding.BtnNextE.visibility = View.GONE
                    }
                }
            }

            R.id.tv_done -> {
                isCheckOk = true
                dialogFinish?.show()
            }

            R.id.bt_huyBo -> {
                answerChoice[count - 1] = mAdapter?.getDA()
                dialogFinish!!.dismiss()
            }

            R.id.bt_dongY, R.id.bt_cancel -> {
                FLAG = 0
                if (isCheckOk) {
                    writeFile(listExam)
                    answerChoice[count - 1] = mAdapter?.getDA()
                    numberCorrect = 0
                    run {
                        var i = 0
                        while (i < SIZE) {
                            Log.d(
                                "Dap an dung cau " + (i + 1),
                                listData?.get(i)?.result.toString()
                            )
                            Log.d(
                                "Dap an lua chon cau " + (i + 1),
                                answerChoice[i].toString()
                            )
                            if (listData?.get(i)?.result?.compareTo(answerChoice[i].toString()) == 0) {
                                numberCorrect++
                                checkTrueFalse[i] = true
                            } else {
                                checkTrueFalse[i] = false
                            }
                            i++
                        }
                    }
                    Common.replaceFragment(
                        requireActivity(), R.id.FragmentLayout, FragmentResults.newInstance(
                            numberCorrect.toString(), typeExam.toString()
                        )
                    )
                    t.interrupt()
                }
                dialogFinish?.dismiss()
            }
        }
    }
}