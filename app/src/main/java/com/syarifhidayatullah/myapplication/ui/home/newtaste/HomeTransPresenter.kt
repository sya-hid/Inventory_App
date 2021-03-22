package com.syarifhidayatullah.myapplication.ui.home.newtaste

import com.syarifhidayatullah.myapplication.network.HttpClient
import com.syarifhidayatullah.myapplication.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomeTransPresenter (private val view:HomeTransContract.View):HomeTransContract.Presenter{
private val mCompositeDisposable:CompositeDisposable?
    init {
         this.mCompositeDisposable= CompositeDisposable()
    }

    override fun getRop() {
               view.showLoading()
    val disposable=HttpClient.getInstance().getApi()!!.get_rop()
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(
        {
            view.dismissLoading()
            it.data?.let { data -> view.onRopSuccess(data) }
            if (it.meta?.status.equals("success", true)) {
                it.data?.let { data -> view.onRopSuccess(data) }
            } else {
                it.meta?.message?.let { it1 -> view.onRopFailed(it1) }
            }
    },
        {

            view.dismissLoading()
//view.onHomeFailed(it.message.toString())
view.onRopFailed(it.getErrorBodyMessage())
        })
        mCompositeDisposable!!.add(disposable)
    }
    override fun subscribe() {

    }

    override fun unsubscribe() {
mCompositeDisposable!!.clear()
    }

}