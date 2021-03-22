package com.syarifhidayatullah.myapplication.ui.order.detail

import com.syarifhidayatullah.myapplication.base.BasePresenter
import com.syarifhidayatullah.myapplication.base.BaseView
import com.syarifhidayatullah.myapplication.model.response.transaction_detail.TransactionDetailResponse

interface DetailPeminjamanContract {
    interface View : BaseView {
        fun onUpdateTransactionSuccess(transactionDetailResponse: TransactionDetailResponse)
        fun onUpdateTransactionFailed(message: String)
    }

    interface Presenter : DetailPeminjamanContract, BasePresenter {
        fun getDetailTransaksi(kd_transaksi:String)
    }
}