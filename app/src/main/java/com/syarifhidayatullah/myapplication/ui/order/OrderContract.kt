package com.syarifhidayatullah.myapplication.ui.order

import com.syarifhidayatullah.myapplication.base.BasePresenter
import com.syarifhidayatullah.myapplication.base.BaseView
import com.syarifhidayatullah.myapplication.model.response.transaction.TransactionResponse


interface OrderContract {
    interface View : BaseView {
        fun onTransactionSuccess(transactionResponse: TransactionResponse)
        fun onTransactionFailed(message: String)

    }

    interface Presenter : OrderContract, BasePresenter {
        fun getTransaction()

    }
}