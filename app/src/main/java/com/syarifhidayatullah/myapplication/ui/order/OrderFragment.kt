package com.syarifhidayatullah.myapplication.ui.order

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.syarifhidayatullah.myapplication.Dashnet
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.login.User
import com.syarifhidayatullah.myapplication.model.response.transaction.Data
import com.syarifhidayatullah.myapplication.model.response.transaction.TransactionResponse
import com.syarifhidayatullah.myapplication.model.response.transaksi_masuk.TransaksiMasukResponse
import com.syarifhidayatullah.myapplication.utils.Cons
import com.syarifhidayatullah.myapplication.utils.Helpers.converLongToTime

import kotlinx.android.synthetic.main.fragment_order.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class OrderFragment : Fragment(),OrderContract.View {
    lateinit var presenter: OrderPresenter
    var progressDialog: Dialog? = null

   var inProgressList: ArrayList<Data>? = ArrayList()
    var pastOrderList: ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_order, container, false)
        return root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
                    layout_empty_order.visibility = View.GONE
            layout_order.visibility = View.VISIBLE
            include_toolbar.visibility = View.VISIBLE

        var user = Dashnet.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        if (userResponse.level.equals("kabag_teknisi")) {
            layout_empty_order.visibility = View.VISIBLE
            layout_order.visibility = View.GONE
            include_toolbar.visibility = View.GONE
        }
        presenter = OrderPresenter(this)
        presenter.getTransaction()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        include_toolbar.toolbar.title = "Your Transactions"
        include_toolbar.toolbar.subtitle = ""

    }

    override fun onTransactionSuccess(transactionResponse: TransactionResponse) {
        val currentDateTimeString = SimpleDateFormat(Cons.DATE_FORMAT_SIMPLE).format(Calendar.getInstance().time)

//        if (transactionResponse.data.isNullOrEmpty()) {
//            layout_empty_order.visibility = View.VISIBLE
//            layout_order.visibility = View.GONE
//            include_toolbar.visibility = View.GONE
//
//        } else {

            for (a in transactionResponse.data.indices) {
                pastOrderList?.add(transactionResponse.data[a])

                if (  transactionResponse.data[a]?.createdAt?.converLongToTime(Cons.DATE_FORMAT_SIMPLE).contains(currentDateTimeString, true)) {
                    inProgressList?.add(transactionResponse.data[a])
                }
            }

            val sectionPagerAdapter = SectionPagerAdapter(childFragmentManager)
            sectionPagerAdapter.setData(inProgressList, pastOrderList)
            viewPager.adapter = sectionPagerAdapter
            tablayout.setupWithViewPager(viewPager)
//        }
    }


    override fun onTransactionFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}