package com.syarifhidayatullah.myapplication.ui.order.add

import com.syarifhidayatullah.myapplication.base.BasePresenter
import com.syarifhidayatullah.myapplication.base.BaseView
import com.syarifhidayatullah.myapplication.model.response.checkout.CheckoutResponse
import com.syarifhidayatullah.myapplication.model.response.keranjang.KeranjangResponse
import com.syarifhidayatullah.myapplication.model.response.teknisi.TeknisiResponse

interface TransContract {
    interface View : BaseView {
        fun onTeknisiSuccess(teknisiResponse: TeknisiResponse)
        fun onTeknisiFailed(message: String)
        fun onKeranjangSuccess(keranjangResponse: KeranjangResponse)
        fun onKeranjangFailed(message: String)
        fun onAddcartSuccess(keranjangResponse: KeranjangResponse)
        fun onAddCartFailed(message: String)
        fun onUpdatecartSuccess(keranjangResponse: KeranjangResponse)
        fun onUpdateCartFailed(message: String)
        fun onDeleteItemCartSuccess(keranjangResponse: KeranjangResponse)
        fun onDeleteItemCartFailed(message: String)
        fun onCheckoutSuccess(message: String)
        fun onCheckoutFailed(message: String)
    }

    interface Presenter : TransContract, BasePresenter {
        fun getTeknisi()
        fun getKeranjang()
        fun addCart(kd_produk:String,jumlah:String)
        fun deleteItemCart(kd_keranjang:String)
        fun updateCart(kd_keranjang: String,kd_produk:String,jumlah:String)
        fun checkout(nik_petugas:String,nik_teknisi:String,jenis_pekerjaan:String,lokasi_pekerjaan:String)
    }
}