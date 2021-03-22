package com.syarifhidayatullah.myapplication.ui.profile.foodmarket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.dummy.ProfileMenuModel
import com.syarifhidayatullah.myapplication.ui.profile.ProfileMenuAdapter
import kotlinx.android.synthetic.main.fragment_profile_food_market.*

class ProfileFoodMarketFragment : Fragment() , ProfileMenuAdapter.ItemAdapterCallback{
    private var menuArrayList: ArrayList<ProfileMenuModel> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_profile_food_market, container, false)
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
        menuArrayList.add(ProfileMenuModel("Rate Apps"))
        menuArrayList.add(ProfileMenuModel("Help Center"))
        menuArrayList.add(ProfileMenuModel("Privacy & Policy"))
        menuArrayList.add(ProfileMenuModel("Term & Condition"))
    }

    override fun onClick(v: View, data: ProfileMenuModel) {
        Toast.makeText(context,"TES", Toast.LENGTH_LONG).show()
    }
}