package com.syarifhidayatullah.myapplication.ui.order.report


import com.syarifhidayatullah.myapplication.network.HttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ReportPresenter(private val view: ReportContract.View):ReportContract.Presenter {
    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }



    override fun getTransaction() {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.get_transaksi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onTransactionReportSuccess(it1) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onTransactionReportFailed(it1) }
                    }
                },
                {

                    view.dismissLoading()
                    view.onTransactionReportFailed(it.message.toString())
                }
            )
        mCompositeDisposable!!.add(disposable)
    }
    override fun getTransactionBydate(start_date:String,end_date:String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.get_transaksi_bydate(start_date, end_date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onTransactionReportSuccessBydate(it1) }
                    } else {
                        it.meta?.message?.let { it1 -> view.onTransactionReportFailedBydate(it1) }
                    }
                },
                {

                    view.dismissLoading()
                    view.onTransactionReportFailedBydate(it.message.toString())
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