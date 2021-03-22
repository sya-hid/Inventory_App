package com.syarifhidayatullah.myapplication.ui.order.grafik

import com.syarifhidayatullah.myapplication.base.BasePresenter
import com.syarifhidayatullah.myapplication.base.BaseView
import com.syarifhidayatullah.myapplication.model.response.grafik.Data

interface GrafikContract {
    interface View : BaseView {
//        fun onProdukSuccess(homeResponse: HomeResponse)
//        fun onProdukFailed(message: String)
        fun onGrafikSuccess(dataGrafik: List<Data>)
        fun onGrafikFailed(message: String)
    }

    interface Presenter : GrafikContract, BasePresenter {
//        fun getHome()
        fun getGrafik()
    }
}