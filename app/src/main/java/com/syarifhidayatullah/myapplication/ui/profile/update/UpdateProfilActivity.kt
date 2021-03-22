package com.syarifhidayatullah.myapplication.ui.profile.update

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import com.syarifhidayatullah.myapplication.Dashnet
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.login.LoginResponse
import com.syarifhidayatullah.myapplication.model.response.login.User
import com.syarifhidayatullah.myapplication.ui.MainActivity
import com.syarifhidayatullah.myapplication.utils.FileUtils
import kotlinx.android.synthetic.main.activity_update_profil.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class UpdateProfilActivity : AppCompatActivity(),ProfilContract.View {
    var progressDialog: Dialog? = null
    lateinit var presenter:ProfilPresenter
     var filePath: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profil)
presenter= ProfilPresenter(this)
initView()
        initListener()
        var user= Dashnet.getApp().getUser()
        var userResponse= Gson().fromJson(user, User::class.java)

        if (!userResponse.profil_picture.isNullOrEmpty()) {
            edtNIK.setFocusable(false)
            edtNIK.setInputType(InputType.TYPE_NULL)
            Glide.with(this)
                .load(userResponse.profil_picture)
                .apply(RequestOptions.circleCropTransform())
                .into(imvProfil)
            edtNIK.setText(userResponse.nik_petugas)
            edtName.setText(userResponse.name)
            edtEmail.setText(userResponse.email)
            edtNoHP.setText(userResponse.phone_number)
            edtLevel.setText(userResponse.level)
            edtLevel.setFocusable(false)
            edtAlamat.setText(userResponse.address)
        }

    }

    private fun initListener() {
        imvProfil.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .start()
        }
        btnContinue.setOnClickListener {
            val profil_picture = FileUtils.getFile(this,filePath)
            if (filePath!=null){
                saveBitmapToFile(profil_picture)
            }
            presenter.getHome(edtNIK.text.toString(),
                edtPassword.text.toString(),
                edtName.text.toString(),
                edtEmail.text.toString(),
                edtNoHP.text.toString(),
                edtAlamat.text.toString(),
                edtLevel.text.toString(),
                profil_picture)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            filePath = data?.data!!

            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(imvProfil)

        } else if (requestCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Task Cancel by User", Toast.LENGTH_LONG).show()
        }
    }
    private fun initView() {
        progressDialog = Dialog(this)
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }


    fun saveBitmapToFile(file: File): File? {
        return try {

            // BitmapFactory options to downsize the image
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            o.inSampleSize = 6
            // factor of downsizing the image
            var inputStream = FileInputStream(file)
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o)
            inputStream.close()

            // The new size we want to scale to
            val REQUIRED_SIZE = 75

            // Find the correct scale value. It should be the power of 2.
            var scale = 1
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                o.outHeight / scale / 2 >= REQUIRED_SIZE
            ) {
                scale *= 2
            }
            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            inputStream = FileInputStream(file)
            val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2)
            inputStream.close()

            // here i override the original image file
            file.createNewFile()
            val outputStream = FileOutputStream(file)
            selectedBitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            file
        } catch (e: Exception) {
            null
        }
    }
    override fun onHomeSuccess(loginResponse: LoginResponse) {

        val gson= Gson()
        val json=gson.toJson(loginResponse.user)
        Dashnet.getApp().setUser(json)
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onHomeFailed(message: String) {

    }

    override fun showLoading() {
progressDialog?.show()
    }

    override fun dismissLoading() {
progressDialog?.dismiss()
    }
}