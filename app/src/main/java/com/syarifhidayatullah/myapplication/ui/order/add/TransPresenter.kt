package com.syarifhidayatullah.myapplication.ui.order.add


import com.syarifhidayatullah.myapplication.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TransPresenter(private val view: TransContract.View):TransContract.Presenter {
    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getTeknisi() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.get_teknisi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onTeknisiSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onTeknisiFailed(it1) }
                    }
                },
                {

                    view.dismissLoading()
                    view.onTeknisiFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun getKeranjang() {
//        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.get_keranjang()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onKeranjangSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onKeranjangFailed(it1) }
                    }
                },
                {

                    view.dismissLoading()
                    view.onKeranjangFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun addCart( kd_produk: String, jumlah: String) {
//        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.add_cart(kd_produk, "1")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onKeranjangSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onKeranjangFailed(it1) }
                    }
                },
                {

                    view.dismissLoading()
                    view.onTeknisiFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun deleteItemCart(kd_keranjang: String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.delete_item_cart(kd_keranjang)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onDeleteItemCartSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onDeleteItemCartFailed(it1) }
                    }
                },
                {

                    view.dismissLoading()
                    view.onDeleteItemCartFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun updateCart(kd_keranjang: String, kd_produk: String, jumlah: String) {
        val disposable = HttpClient.getInstance().getApi()!!.update_cart(kd_keranjang,kd_produk,jumlah)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onUpdatecartSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onUpdateCartFailed( it1) }
                    }
                },
                {

                    view.dismissLoading()
                    view.onUpdateCartFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun checkout(
        nik_petugas: String,
        nik_teknisi: String,
        jenis_pekerjaan: String,
        lokasi_pekerjaan: String
    ) {
        val disposable = HttpClient.getInstance().getApi()!!.checkout(nik_petugas,nik_teknisi,jenis_pekerjaan,lokasi_pekerjaan)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.meta?.message?.let { it1 -> view.onCheckoutSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onCheckoutFailed( it1) }
                    }
                },
                {

                    view.dismissLoading()
                    view.onCheckoutFailed(it.message.toString())
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