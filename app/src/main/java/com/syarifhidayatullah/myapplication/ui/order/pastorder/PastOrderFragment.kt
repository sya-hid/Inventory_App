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
import com.syarifhidayatullah.myapplication.model.response.transaksi_masuk.Data
import com.syarifhidayatullah.myapplication.model.response.transaksi_masuk.TransaksiMasukResponse
import com.syarifhidayatullah.myapplication.ui.order.detail_masuk.DetailMasukActivity
import com.syarifhidayatullah.myapplication.utils.Cons
import kotlinx.android.synthetic.main.fragment_pastorder.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class PastOrderFragment : Fragment(),PastOrderContract.View,PastOrderAdapter.ItemAdapterCallback{
 //   private var menuArrayList: ArrayList<ProfileMenuModel> = ArrayList()

    private var adapter: PastOrderAdapter?=null
//    var pastOrderList:ArrayList<Data>? = ArrayList()
   private lateinit var presenter:PastOrderPresenter
    var progressDialog: Dialog? = null
    private lateinit var datePicker1:DatePickerDialog
    private lateinit var datePicker2:DatePickerDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val root=inflater.inflate(R.layout.fragment_pastorder, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)
//      pastOrderList = arguments?.getParcelableArrayList("data")
        selectedDate1.setFocusable(false)
        selectedDate1.setInputType(InputType.TYPE_NULL)
        selectedDate2.setFocusable(false)
        selectedDate2.setInputType(InputType.TYPE_NULL)

        presenter= PastOrderPresenter(this)
        presenter.getTransactionMasuk()
        initView()
//if(pastOrderList!!.isNotEmpty()){
//    adapter= PastOrderAdapter(pastOrderList!!,this)
//    val layoutManager=LinearLayoutManager(activity)
//    rcvMasukList.layoutManager=layoutManager
//
//    rcvMasukList.adapter=adapter
//}
        val mcurrentTime = Calendar.getInstance()
        val year = mcurrentTime.get(Calendar.YEAR)
        val month = mcurrentTime.get(Calendar.MONTH)
        val day = mcurrentTime.get(Calendar.DAY_OF_MONTH)

         datePicker1 = DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                selectedDate1.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth))
            }
        }, year, month, day);
         datePicker2 = DatePickerDialog(requireContext(), object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                selectedDate2.setText(String.format("%d-%d-%d", year, month + 1, dayOfMonth))
            }
        }, year, month, day);

        selectedDate1.setOnClickListener(View.OnClickListener { datePicker1.show()})
        selectedDate2.setOnClickListener(View.OnClickListener { datePicker2.show()})

selectDate.setOnClickListener {
    presenter.getTransactionMasukByDate(selectedDate1.text.toString(),selectedDate2.text.toString())
}

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
        val detail = Intent(activity, DetailMasukActivity::class.java).putExtra("data", data)
        startActivity(detail)
//        Toast.makeText(activity, data.kdProduk, Toast.LENGTH_SHORT).show()
    }

    override fun onTransactionMasukSuccess(transaksiMasukResponse: TransaksiMasukResponse) {


//        for (a in transaksiMasukResponse.data.indices){
//        pastOrderList?.add(transaksiMasukResponse.data[a])
//
//       }
        adapter= PastOrderAdapter(transaksiMasukResponse.data,this)
        val layoutManager=LinearLayoutManager(activity)
        rcvMasukList.layoutManager=layoutManager

        rcvMasukList.adapter=adapter
    }

    override fun onTransactionMasukFailed(message: String) {
        Toast.makeText(activity, "kok gagal", Toast.LENGTH_SHORT).show()
    }

    override fun onTransactionMasukSuccessByDate(transaksiMasukResponse: TransaksiMasukResponse) {
        adapter= PastOrderAdapter(transaksiMasukResponse.data,this)
        val layoutManager=LinearLayoutManager(activity)
        rcvMasukList.layoutManager=layoutManager

        rcvMasukList.adapter=adapter
    }

    override fun onTransactionMasukFailedByDate(message: String) {
        Toast.makeText(activity, "kok gagal", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
//progressDialog?.show()
    }

    override fun dismissLoading() {
//progressDialog?.dismiss()
    }



}