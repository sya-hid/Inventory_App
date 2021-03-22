package com.syarifhidayatullah.myapplication.ui.auth.signin

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.syarifhidayatullah.myapplication.Dashnet
import com.syarifhidayatullah.myapplication.ui.MainActivity
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.login.LoginResponse
import com.syarifhidayatullah.myapplication.ui.auth.AuthActivity
import com.syarifhidayatullah.myapplication.utils.Cons
import kotlinx.android.synthetic.main.fragment_signin.*

class SigninFragment : Fragment(),SignInContract.View {
lateinit var presenter: SigninPresenter
    var progressDialog:Dialog?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_signin, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter= SigninPresenter(this)
//        if (!Dashnet.getApp().getToken().isNullOrEmpty()){
//            val intent= Intent(activity, MainActivity::class.java)
//            startActivity(intent)
//            activity?.finish()
//        }
//        val intent= Intent(activity, MainActivity::class.java)
//        startActivity(intent)
     //   initDummy()
        initView()
        initListener()



//            val intent= Intent(activity,MainActivity::class.java)
//            startActivity(intent)
//            activity?.finish()


    }

    private fun initListener() {
        btnSignIn.setOnClickListener {
            var email=edtEmail.text.toString()
            var password=edtPassword.text.toString()
            if (email.isNullOrEmpty()){
                edtEmail.error="Silahkan isi NIK Anda"
                edtEmail.requestFocus()
            }else if(password.isNullOrEmpty()){
                edtPassword.error="Silahkan isi Password"
                edtPassword.requestFocus()
            }else {
                presenter.submitLogin(email, password)
            }
        }
        btnSignUp.setOnClickListener {
            val intent= Intent(activity, AuthActivity::class.java)
            intent.putExtra("page_request", Cons.AUTH_SIGN_UP)
            startActivity(intent)
        }
    }

    private fun initDummy() {
edtEmail.setText("jennie.kim@blackpink.com")
        edtPassword.setText("12345678")
    }

    private fun initView(){
    progressDialog= Dialog(requireContext())
    val dialogLayout=layoutInflater.inflate(R.layout.dialog_loader,null)
    progressDialog?.let {
        it.setContentView(dialogLayout)
        it.setCancelable(false)
        it.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}
    override fun onLoginSuccess(loginResponse: LoginResponse) {
     Dashnet.getApp().setToken(loginResponse.access_token)

        val gson= Gson()
        val json=gson.toJson(loginResponse.user)
        Dashnet.getApp().setUser(json)
        val intent= Intent(activity,
            MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onLoginFailed(message: String) {
     Toast.makeText(activity,message,Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
progressDialog?.show()
     }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

}