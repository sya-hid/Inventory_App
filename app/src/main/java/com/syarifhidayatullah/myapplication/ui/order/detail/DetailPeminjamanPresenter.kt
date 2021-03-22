package com.syarifhidayatullah.myapplication.ui.order.detail

import android.view.View
import com.syarifhidayatullah.myapplication.network.HttpClient
import com.syarifhidayatullah.myapplication.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPeminjamanPresenter (private val view:DetailPeminjamanContract.View) : DetailPeminjamanContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun getDetailTransaksi(kd_transaksi:String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.get_detail_transaksi(kd_transaksi)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()

                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { data -> view.onUpdateTransactionSuccess(data) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onUpdateTransactionFailed(it1) }
                    }

                },
                {
                    view.dismissLoading()
                    view.onUpdateTransactionFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {}
    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}