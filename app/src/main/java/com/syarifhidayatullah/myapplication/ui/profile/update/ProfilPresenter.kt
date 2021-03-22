package com.syarifhidayatullah.myapplication.ui.profile.update

import android.net.Uri
import android.text.Editable
import com.syarifhidayatullah.myapplication.network.HttpClient
import com.syarifhidayatullah.myapplication.utils.Helpers.getErrorBodyMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ProfilPresenter (private val view:ProfilContract.View):ProfilContract.Presenter{
private val mCompositeDisposable:CompositeDisposable?
    init {
         this.mCompositeDisposable= CompositeDisposable()
    }
    override fun getHome(
        nik_petugas: String,
        password:String,
        name:String,
        email:String,
        phoneNumber:String,
        alamat:String,
        level:String,
        profil_picture:File?) {

        val requestBody: RequestBody
        val multipartBody: MultipartBody.Part
        if (profil_picture != null){
            requestBody= RequestBody.create(MediaType.parse("image/*"), profil_picture)
            multipartBody= MultipartBody.Part.createFormData(
                "profil_picture",profil_picture.name, requestBody
            )
        }else{
            requestBody= RequestBody.create(MediaType.parse("image/*"), "")
            multipartBody= MultipartBody.Part.createFormData("profil_picture","", requestBody)
        }

//        var profileImageFile = File(profil_picture!!.path)
//        val profileImageRequestBody =RequestBody.create(MediaType.parse("multipart/form-data"), profileImageFile)
//        val profileImageParms = MultipartBody.Part.createFormData("file", profileImageFile.name, profileImageRequestBody)

        view.showLoading()
    val disposable=HttpClient.getInstance().getApi()!!.update_profil(nik_petugas,password,name,email,phoneNumber,alamat,level,multipartBody)
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






    override fun subscribe() {

    }

    override fun unsubscribe() {
mCompositeDisposable!!.clear()
    }

}