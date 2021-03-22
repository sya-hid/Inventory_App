package com.syarifhidayatullah.myapplication.ui.profile.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.dummy.ProfileMenuModel
import com.syarifhidayatullah.myapplication.ui.profile.ProfileMenuAdapter
import com.syarifhidayatullah.myapplication.ui.profile.update.UpdateProfilActivity
import kotlinx.android.synthetic.main.fragment_profile_account.*


class ProfileAccountFragment : Fragment(), ProfileMenuAdapter.ItemAdapterCallback {
    private var menuArrayList: ArrayList<ProfileMenuModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_profile_account, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDataDummy()
        var adapter= ProfileMenuAdapter(menuArrayList,this)
        var layoutManager: RecyclerView.LayoutManager=
            LinearLayoutManager(activity)
        rcvMenuProfileList.layoutManager=layoutManager
        rcvMenuProfileList.adapter=adapter



    }
    fun initDataDummy(){
menuArrayList= ArrayList()
        menuArrayList.add(ProfileMenuModel("Edit Profil"))
        menuArrayList.add(ProfileMenuModel("Logout"))
    }

    override fun onClick(v: View, data: ProfileMenuModel) {

        if (data.title == "Edit Profil"){
            val activity=Intent(activity,UpdateProfilActivity::class.java)
            startActivity(activity)
        }
        if (data.title == "Logout"){
//            val activity=Intent(activity,UpdateProfilActivity::class.java)
//            startActivity(activity)
            ActivityCompat.finishAffinity(requireActivity())
        }

    }
}