package com.syarifhidayatullah.myapplication.ui.order.grafik

import com.syarifhidayatullah.myapplication.model.response.grafik.Data
import com.syarifhidayatullah.myapplication.network.HttpClient
import com.syarifhidayatullah.myapplication.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GrafikPresenter (private val view:GrafikContract.View) : GrafikContract.Presenter {

    private val mCompositeDisposable : CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

//

    override fun getGrafik() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.get_detail_transaksi_grafik()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
//                    it.data?.let { list: List<Data> ->  }
//                    it.data?.let { data -> view.onGrafikSuccess(data) }
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { data -> view.onGrafikSuccess(data) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onGrafikFailed(it1) }
                    }

                },
                {
                    view.dismissLoading()
                    view.onGrafikFailed(it.getErrorBodyMessage())
                })
        mCompositeDisposable!!.add(disposable)
    }

    override fun subscribe() {}
    override fun unsubscribe() {
        mCompositeDisposable!!.clear()
    }

}