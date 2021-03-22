package com.syarifhidayatullah.myapplication.ui.produk

import com.syarifhidayatullah.myapplication.base.BasePresenter
import com.syarifhidayatullah.myapplication.base.BaseView
import com.syarifhidayatullah.myapplication.model.response.home.HomeResponse
import com.syarifhidayatullah.myapplication.model.response.kategori.KategoriResponse
import java.io.File

interface AddProdukContract {
    interface View : BaseView {
        fun onHomeSuccess(homeResponse: HomeResponse)
        fun onHomeFailed(message: String)
        fun onKategoriSuccess(kategoriResponse: KategoriResponse)
        fun onKategoriFailed(message: String)
        fun onAddProdukSuccess(produkResponse: HomeResponse)
        fun onAddProdukFailed(message: String)

    }

    interface Presenter : AddProdukContract, BasePresenter {
        fun getHome(kd_produk: String)
        fun getKategori()
        fun add_produk(kd_produk: String, kd_kategori:String, nama_produk:String, gambar_produk: File?,jumlah:Int,keterangan:String)
    }
}