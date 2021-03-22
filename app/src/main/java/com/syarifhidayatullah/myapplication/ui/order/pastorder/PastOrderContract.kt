package com.syarifhidayatullah.myapplication.ui.order.pastorder

import com.syarifhidayatullah.myapplication.base.BasePresenter
import com.syarifhidayatullah.myapplication.base.BaseView
import com.syarifhidayatullah.myapplication.model.response.transaksi_masuk.TransaksiMasukResponse
import java.util.*

interface PastOrderContract {
    interface View : BaseView {
        fun onTransactionMasukSuccess(transaksiMasukResponse: TransaksiMasukResponse)
        fun onTransactionMasukFailed(message: String)
        fun onTransactionMasukSuccessByDate(transaksiMasukResponse: TransaksiMasukResponse)
        fun onTransactionMasukFailedByDate(message: String)
    }

    interface Presenter : PastOrderContract, BasePresenter {

        fun getTransactionMasuk()
        fun getTransactionMasukByDate(start_date: String, end_date: String)
    }
}