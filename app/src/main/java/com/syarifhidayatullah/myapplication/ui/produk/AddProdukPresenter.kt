package com.syarifhidayatullah.myapplication.ui.produk

import com.syarifhidayatullah.myapplication.network.HttpClient
import com.syarifhidayatullah.myapplication.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class AddProdukPresenter(private val view: AddProdukContract.View) : AddProdukContract.Presenter {
    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getHome(kd_produk: String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.get_produk_by_kode(kd_produk)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onHomeSuccess(it1) }
                    } else {
                        //    it.meta?.message?.let { it1 -> view.onHomeFailed(it1) }
                        view.onHomeFailed(it.meta?.message.toString())
                    }
                },
                {

                    view.dismissLoading()
//view.onHomeFailed(it.message.toString())
                    view.onHomeFailed(it.getErrorBodyMessage())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun getKategori() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.get_kategori()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onKategoriSuccess(it1) }
                    } else {
                        //    it.meta?.message?.let { it1 -> view.onHomeFailed(it1) }
                        view.onKategoriFailed(it.meta?.message.toString())
                    }
                },
                {

                    view.dismissLoading()
//view.onHomeFailed(it.message.toString())
                    view.onKategoriFailed(it.getErrorBodyMessage())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun add_produk(
        kd_produk: String,
        kd_kategori: String,
        nama_produk: String,
        gambar_produk: File?,
        jumlah: Int,
        keterangan: String
    ) {
        val requestBody: RequestBody
        val multipartBody: MultipartBody.Part
        if (gambar_produk != null){
            requestBody= RequestBody.create(MediaType.parse("image/*"), gambar_produk)
            multipartBody= MultipartBody.Part.createFormData(
                "gambar_produk",gambar_produk.name, requestBody
            )
        }else{
            requestBody= RequestBody.create(MediaType.parse("image/*"), "")
            multipartBody= MultipartBody.Part.createFormData("gambar_produk","", requestBody)
        }
        view.showLoading()
        val disposable=HttpClient.getInstance().getApi()!!.add_produk(kd_produk,kd_kategori,nama_produk,multipartBody,jumlah,keterangan)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success",true)){
                        it.data?.let { it1 -> view.onAddProdukSuccess(it1) }
                    }else{
                        //    it.meta?.message?.let { it1 -> view.onHomeFailed(it1) }
                        view.onAddProdukFailed(it.meta?.message.toString())
                    }
                },
                {

                    view.dismissLoading()
//view.onHomeFailed(it.message.toString())
                    view.onAddProdukFailed(it.getErrorBodyMessage())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }


    override fun subscribe() {

    }

    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}