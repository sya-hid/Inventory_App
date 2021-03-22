package com.syarifhidayatullah.myapplication.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.syarifhidayatullah.myapplication.Dashnet
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.login.User
import kotlinx.android.synthetic.main.fragment_notifications.*

class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val sectionPagerAdapter= SectionPagerAdapter(
            childFragmentManager
        )
        viewPager.adapter=sectionPagerAdapter
        tablayout.setupWithViewPager(viewPager)

        var user= Dashnet.getApp().getUser()
        var userResponse=Gson().fromJson(user, User::class.java)

        if (!userResponse.profil_picture.isNullOrEmpty()){
            Glide.with(requireActivity())
                .load(userResponse.profil_picture)
                .apply(RequestOptions.circleCropTransform())
                .into(imvProfil)
txvNama.text=userResponse.name
            txvEmail.text=userResponse.email
        }



    }



}