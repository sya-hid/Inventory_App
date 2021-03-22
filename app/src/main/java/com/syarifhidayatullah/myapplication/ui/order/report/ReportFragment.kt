package com.syarifhidayatullah.myapplication.ui.order.pastorder

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.transaction.Data
import com.syarifhidayatullah.myapplication.model.response.transaction.TransactionResponse
import com.syarifhidayatullah.myapplication.ui.order.detail.DetailPeminjamanActivity
import com.syarifhidayatullah.myapplication.ui.order.report.ReportContract
import com.syarifhidayatullah.myapplication.ui.order.report.ReportPresenter
import kotlinx.android.synthetic.main.fragment_report.*
import java.util.*
import kotlin.collections.ArrayList


class ReportFragment : Fragment(), ReportContract.View, ReportAdapter.ItemAdapterCallback {
    //   private var menuArrayList: ArrayList<ProfileMenuModel> = ArrayList()

    private var adapter: ReportAdapter? = null

    //    var pastOrderList:ArrayList<Data>? = ArrayList()
    var progressDialog: Dialog? = null
    private lateinit var datePicker1: DatePickerDialog
    private lateinit var datePicker2: DatePickerDialog
    private lateinit var presenter: ReportPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_report, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        pastOrderList = arguments?.getParcelableArrayList("data")

        selectedDate1.setFocusable(false)
        selectedDate1.setInputType(InputType.TYPE_NULL)
        selectedDate2.setFocusable(false)
        selectedDate2.setInputType(InputType.TYPE_NULL)
        presenter = ReportPresenter(this)
        presenter.getTransaction()

        initView()
        val mcurrentTime = Calendar.getInstance()
        val year = mcurrentTime.get(Calendar.YEAR)
        val month = mcurrentTime.get(Calendar.MONTH)
        val day = mcurrentTime.get(Calendar.DAY_OF_MONTH)

        datePicker1 =
            DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    selectedDate1.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth))
                }
            }, year, month, day);
        datePicker2 =
            DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    selectedDate2.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth))
                }
            }, year, month, day);
//        mEditInit = findViewById(R.id.selectedDate1) as EditText
        selectedDate1.setOnClickListener(View.OnClickListener { datePicker1.show() })
        selectedDate2.setOnClickListener(View.OnClickListener { datePicker2.show() })


        selectDate.setOnClickListener {
            presenter.getTransactionBydate(
                selectedDate1.text.toString(),
                selectedDate2.text.toString()
            )
        }

//            pastOrderList = arguments?.getParcelableArrayList("data")
//
//            if (!pastOrderList.isNullOrEmpty()){
//
//
//                adapter= ReportAdapter(pastOrderList!!,this)
//                val layoutManager=LinearLayoutManager(activity)
//                rcvOrderList.layoutManager=layoutManager
//
//                rcvOrderList.adapter=adapter
//            }


    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailPeminjamanActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }

    override fun onTransactionReportSuccess(transactionResponse: TransactionResponse) {
//        for (a in transactionResponse.data.indices){
//pastOrderList?.add(transactionResponse.data[a])
//        }
        adapter = ReportAdapter(transactionResponse.data, this)
        val layoutManager = LinearLayoutManager(activity)
        rcvOrderList.layoutManager = layoutManager

        rcvOrderList.adapter = adapter
    }

    override fun onTransactionReportFailed(message: String) {

    }

    override fun onTransactionReportSuccessBydate(transactionResponse: TransactionResponse) {
        adapter = ReportAdapter(transactionResponse.data, this)
        val layoutManager = LinearLayoutManager(activity)
        rcvOrderList.layoutManager = layoutManager

        rcvOrderList.adapter = adapter
    }

    override fun onTransactionReportFailedBydate(message: String) {
        Toast.makeText(activity, "Something wrong", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
//        progressDialog?.show()
    }

    override fun dismissLoading() {
//        progressDialog?.dismiss()
    }


}