package com.syarifhidayatullah.myapplication.ui.order.inprogress

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.syarifhidayatullah.myapplication.Dashnet
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.login.User
import com.syarifhidayatullah.myapplication.model.response.transaction.Data
import com.syarifhidayatullah.myapplication.ui.order.add.TransActivity
import com.syarifhidayatullah.myapplication.ui.order.detail.DetailPeminjamanActivity
import com.syarifhidayatullah.myapplication.utils.Cons
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_inprogress.*
import kotlinx.android.synthetic.main.fragment_inprogress.fb_add
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class InProgressFragment : Fragment(),InProgressAdapter.ItemAdapterCallback{
 //   private var menuArrayList: ArrayList<ProfileMenuModel> = ArrayList()

    private var adapter:InProgressAdapter?=null
    var inProgressList:ArrayList<Data>? = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root= inflater.inflate(R.layout.fragment_inprogress, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        inProgressList = arguments?.getParcelableArrayList("data")

        if (!inProgressList.isNullOrEmpty()){
            adapter=InProgressAdapter(inProgressList!!,this)
            val layoutManager=LinearLayoutManager(activity)
            rcvOrderList.layoutManager=layoutManager

            rcvOrderList.adapter=adapter
        }
        var user = Dashnet.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        if (userResponse.level.equals("admin")) {
            fb_add.visibility=View.GONE
        }else{
            fb_add.visibility=View.VISIBLE
        }
        fb_add.setOnClickListener {
            val detail = Intent(activity, TransActivity::class.java)
            startActivity(detail)

        }
    }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailPeminjamanActivity::class.java).putExtra("data", data)
        startActivity(detail)
    }
}