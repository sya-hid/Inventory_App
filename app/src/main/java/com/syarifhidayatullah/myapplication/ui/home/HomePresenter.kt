package com.syarifhidayatullah.myapplication.ui.home

import com.syarifhidayatullah.myapplication.network.HttpClient
import com.syarifhidayatullah.myapplication.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter (private val view:HomeContract.View):HomeContract.Presenter{
private val mCompositeDisposable:CompositeDisposable?
    init {
         this.mCompositeDisposable= CompositeDisposable()
    }
    override fun getHome() {
        view.showLoading()
    val disposable=HttpClient.getInstance().getApi()!!.home()
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(
        {
            view.dismissLoading()
        if (it.meta?.status.equals("success",true)){
            it.data?.let { it1 -> view.onHomeSuccess(it1) }
        }else{
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

//    override fun get_rop() {
//        view.showLoading()
//        val disposable = HttpClient.getInstance().getApi()!!.get_rop()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    view.dismissLoading()
////                    it.data?.let { list: List<DataX> ->  }
//                    it.data?.let { data -> view.onRopSuccess(data) }
//                    if (it.meta?.status.equals("success", true)) {
//                        it.data?.let { data -> view.onRopSuccess(data) }
//                    } else {
//                        it.meta?.message?.let { it1 -> view.onRopFailed(it1) }
//                    }
//
//                },
//                {
//                    view.dismissLoading()
//                    view.onRopFailed(it.getErrorBodyMessage())
//                })
//        mCompositeDisposable!!.add(disposable)
//    }


    override fun subscribe() {

    }

    override fun unsubscribe() {
mCompositeDisposable!!.clear()
    }

}