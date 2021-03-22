package com.syarifhidayatullah.myapplication.ui.home

import com.syarifhidayatullah.myapplication.base.BasePresenter
import com.syarifhidayatullah.myapplication.base.BaseView
import com.syarifhidayatullah.myapplication.model.response.home.HomeResponse

interface HomeContract {
    interface View : BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message: String)
//        fun onRopSuccess(rop:List<Data>)
//        fun onRopFailed(message: String)

    }

    interface Presenter : HomeContract, BasePresenter {
        fun getHome()
//fun get_rop()
    }
}