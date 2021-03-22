package com.syarifhidayatullah.myapplication.ui.auth.signin


import com.syarifhidayatullah.myapplication.network.HttpClient
import com.syarifhidayatullah.myapplication.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SigninPresenter(private val view: SignInContract.View)  : SignInContract.Presenter{
    private val mCompositeDisposable: CompositeDisposable?

    init {
        this.mCompositeDisposable = CompositeDisposable()
    }

    override fun submitLogin(nik_petugas: String, password: String) {
        view.showLoading()
        val disposable = HttpClient.getInstance().getApi()!!.login(nik_petugas, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    view.dismissLoading()
                    if (it.meta?.status.equals("success", true)) {
                        it.data?.let { it1 -> view.onLoginSuccess(it1) }
                    } else {
//                        it.meta?.message?.let { it1 -> view.onLoginFailed(it1) }
                        view.onLoginFailed(it.meta?.message.toString())
                    }
                },
                {

                    view.dismissLoading()
                    view.onLoginFailed(it.getErrorBodyMessage())
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