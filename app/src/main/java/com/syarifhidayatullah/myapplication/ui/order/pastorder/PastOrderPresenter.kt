package com.syarifhidayatullah.myapplication.ui.order.pastorder


import com.syarifhidayatullah.myapplication.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class PastOrderPresenter(private val view: PastOrderContract.View):PastOrderContract.Presenter {
    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }


    override fun getTransactionMasukByDate(start_date: String, end_date:String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.lap_transaksi(start_date,end_date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onTransactionMasukSuccessByDate(it1) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onTransactionMasukFailedByDate(it1) }
                    }
                },
                {

                    view.dismissLoading()
                    view.onTransactionMasukFailedByDate(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }

    override fun getTransactionMasuk() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.get_lap_transaksi_masuk()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onTransactionMasukSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onTransactionMasukFailed(it1) }
                    }
                },
                {

                    view.dismissLoading()
                    view.onTransactionMasukFailed(it.message.toString())
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