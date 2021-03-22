package com.syarifhidayatullah.myapplication.ui.order.report

import com.syarifhidayatullah.myapplication.base.BasePresenter
import com.syarifhidayatullah.myapplication.base.BaseView
import com.syarifhidayatullah.myapplication.model.response.transaction.TransactionResponse

interface ReportContract {
    interface View : BaseView {
        fun onTransactionReportSuccess(transactionResponse: TransactionResponse)
        fun onTransactionReportFailed(message: String)

        fun onTransactionReportSuccessBydate(transactionResponse: TransactionResponse)
        fun onTransactionReportFailedBydate(message: String)

    }

    interface Presenter : ReportContract, BasePresenter {
        fun getTransactionBydate( start_date: String, end_date: String)
        fun getTransaction()

    }
}