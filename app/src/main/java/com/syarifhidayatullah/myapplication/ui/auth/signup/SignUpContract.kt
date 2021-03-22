package com.syarifhidayatullah.myapplication.ui.auth.signup

import android.net.Uri
import com.syarifhidayatullah.myapplication.model.response.login.LoginResponse
import com.syarifhidayatullah.myapplication.base.BasePresenter
import com.syarifhidayatullah.myapplication.base.BaseView
import com.syarifhidayatullah.myapplication.model.request.RegisterRequest

interface SignUpContract {
    interface View : BaseView {
        fun onRegisterSuccess(loginResponse: LoginResponse,view:android.view.View)
        fun onRegisterPhotoSuccess(view:android.view.View)
        fun onRegisterFailed(message: String)
    }

    interface Presenter : SignUpContract, BasePresenter {
        fun submitRegister(registerRequest: RegisterRequest,view:android.view.View)
        fun submitPhotoRegister(filePath:Uri,view:android.view.View)
    }
}