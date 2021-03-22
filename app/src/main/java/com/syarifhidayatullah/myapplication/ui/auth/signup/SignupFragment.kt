package com.syarifhidayatullah.myapplication.ui.auth.signup

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.request.RegisterRequest
import com.syarifhidayatullah.myapplication.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : Fragment() {
    var filePath: Uri? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_signup, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDummy()
        initListener()
    }

    private fun initListener() {
        imvProfil.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .start()
        }

        btnContinue.setOnClickListener {
            var nama = edtName.text.toString()
            var email = edtEmail.text.toString()
            var password = edtPassword.text.toString()
            if (nama.isNullOrEmpty()) {
                edtName.error = "Silahkan isi Nama Anda"
                edtName.requestFocus()
            } else if (email.isNullOrEmpty()) {
                edtEmail.error = "Silahkan isi Email Anda"
                edtEmail.requestFocus()
            } else if (password.isNullOrEmpty()) {
                edtPassword.error = "Silahkan isi Password Anda"
                edtPassword.requestFocus()
            } else {
                var data = RegisterRequest(
                    nama, email, password, password, "", "", "", "", filePath
                )
                var bundle = Bundle()
                bundle.putParcelable("data", data)
                Navigation.findNavController(it)
                    .navigate(R.id.action_signup_address, bundle)
                (activity as AuthActivity).toolbarSignUpAddress()
            }


        }
    }

    private fun initDummy() {
        edtName.setText("Syarif Hidayat")
        edtEmail.setText("syarifhidayat@mail.com")
        edtPassword.setText("12345678")
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
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Task Cancel by User", Toast.LENGTH_LONG).show()
        }
    }

}