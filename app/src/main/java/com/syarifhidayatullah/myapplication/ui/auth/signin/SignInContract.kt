package com.syarifhidayatullah.myapplication.ui.auth.signin


import com.syarifhidayatullah.myapplication.base.BasePresenter
import com.syarifhidayatullah.myapplication.base.BaseView
import com.syarifhidayatullah.myapplication.model.response.login.LoginResponse

interface SignInContract {
    interface View : BaseView {
        fun onLoginSuccess(loginResponse: LoginResponse)
        fun onLoginFailed(message: String)
    }

    interface Presenter : SignInContract, BasePresenter {
        fun submitLogin(nik_petugas: String, password: String)
    }
}