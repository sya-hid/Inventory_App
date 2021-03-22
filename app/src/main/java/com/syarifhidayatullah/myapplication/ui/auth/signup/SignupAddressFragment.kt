package com.syarifhidayatullah.myapplication.ui.auth.signup

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.syarifhidayatullah.myapplication.Dashnet
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.request.RegisterRequest
import com.syarifhidayatullah.myapplication.model.response.login.LoginResponse
import com.syarifhidayatullah.myapplication.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.fragment_signup_address.*

class SignupAddressFragment : Fragment(), SignUpContract.View {
    private lateinit var data: RegisterRequest
    lateinit var presenter: SignUpPresenter
    var progressDialog: Dialog? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_signup_address, container, false)
return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = SignUpPresenter(this)

        data =arguments?.getParcelable<RegisterRequest>("data")!!

       initDummy()
       initListener()
        initView()
    }

    private fun initListener() {

        btnSignUpNow.setOnClickListener { view ->
            var phone = edtPhoneNumber.text.toString()
            var address = edtAddress.text.toString()
            var house = edtHouseNumber.text.toString()
            var city = edtCity.text.toString()
//Toast.makeText(activity,phone+address+city+house,Toast.LENGTH_LONG).show()
            data.let {
                it.address = address
                it.houseNumber = house
                it.city = city
                it.phoneNumber = phone
            }
      //      Toast.makeText(activity,data.phoneNumber+data.filePath+data.name,Toast.LENGTH_LONG).show()
            if (phone.isNullOrEmpty()) {
                edtPhoneNumber.error = "Silahkan Isi data"
                edtPhoneNumber.requestFocus()
            } else if (address.isNullOrEmpty()) {
                edtAddress.error = "Silahkan Isi data"
                edtAddress.requestFocus()
            } else if (house.isNullOrEmpty()) {
                edtHouseNumber.error = "Silahkan Isi data"
                edtHouseNumber.requestFocus()
            } else if (city.isNullOrEmpty()) {
                edtCity.error = "Silahkan Isi data"
                edtCity.requestFocus()
            } else {
                  //  presenter.submitRegister(data,it)
                    presenter.submitRegister(data,view)
            //   presenter.submitPhotoRegister(data.filePath!!, it)
            }

        }
    }

    private fun initDummy() {
        edtPhoneNumber.setText("08215360399")
        edtAddress.setText("Jl. YosSudarso")
        edtHouseNumber.setText("23 A")
        edtCity.setText("Pekanbaru")
    }

    override fun onRegisterSuccess(loginResponse: LoginResponse, view: View) { Dashnet.getApp().setToken(loginResponse.access_token)

        val gson = Gson()
        val json = gson.toJson(loginResponse.user)
        Dashnet.getApp().setUser(json)

        if (data.filePath == null) {
            Navigation.findNavController(view)
                .navigate(R.id.action_signup_success, null)
            (activity as AuthActivity).toolbarSignUpSuccess()
        } else {
            presenter.submitPhotoRegister(data.filePath!!, view)
        }

    }

    override fun onRegisterPhotoSuccess(view: View) {

        Navigation.findNavController(view)
            .navigate(R.id.action_signup_success, null)
        (activity as AuthActivity).toolbarSignUpSuccess()
//Toast.makeText(activity,"sukses",Toast.LENGTH_LONG).show()
    }

    override fun onRegisterFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun showLoading() {
       progressDialog?.show()
    }

    override fun dismissLoading() {
       progressDialog?.dismiss()
    }
}