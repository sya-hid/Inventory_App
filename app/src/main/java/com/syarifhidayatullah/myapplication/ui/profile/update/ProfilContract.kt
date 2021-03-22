package com.syarifhidayatullah.myapplication.ui.profile.update

import android.net.Uri
import android.text.Editable
import com.syarifhidayatullah.myapplication.base.BasePresenter
import com.syarifhidayatullah.myapplication.base.BaseView
import com.syarifhidayatullah.myapplication.model.response.login.LoginResponse
import com.syarifhidayatullah.myapplication.model.response.login.User
import java.io.File

interface ProfilContract {
    interface View : BaseView {
        fun onHomeSuccess(loginResponse: LoginResponse)
        fun onHomeFailed(message: String)

    }

    interface Presenter : ProfilContract, BasePresenter {
        fun getHome(
            nik_petugas: String,
            password:String,
            name:String,
            email:String,
            phoneNumber:String,
            alamat:String,
            level:String,
            profil_picture: File?)

    }
}