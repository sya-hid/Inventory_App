package com.syarifhidayatullah.myapplication.ui.home.newtaste

import com.syarifhidayatullah.myapplication.base.BasePresenter
import com.syarifhidayatullah.myapplication.base.BaseView
import com.syarifhidayatullah.myapplication.model.response.rop.Data

interface HomeTransContract {
    interface View : BaseView {
        //        fun onHomeSuccess(transactionDetailResponse: TransactionDetailResponse)
//        fun onHomeFailed(message: String)
        fun onRopSuccess(rop: Data)
        fun onRopFailed(message: String)
    }

    interface Presenter : HomeTransContract, BasePresenter {
        //        fun getDataTrans()
        fun getRop()
    }
}