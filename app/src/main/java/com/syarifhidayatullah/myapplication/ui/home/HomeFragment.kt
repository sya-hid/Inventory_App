package com.syarifhidayatullah.myapplication.ui.home

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.syarifhidayatullah.myapplication.Dashnet

import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.dummy.HomeModel
import com.syarifhidayatullah.myapplication.model.response.home.Data
import com.syarifhidayatullah.myapplication.model.response.home.HomeResponse
import com.syarifhidayatullah.myapplication.model.response.login.User
import com.syarifhidayatullah.myapplication.model.response.transaction_detail.TransactionDetailResponse
import com.syarifhidayatullah.myapplication.ui.detail.DetailActivity
import com.syarifhidayatullah.myapplication.ui.produk.AddProdukActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeAdapter.ItemAdapterCallback, HomeContract.View {

    private var newTasteList: ArrayList<Data> = ArrayList()
    private var popularList: ArrayList<Data> = ArrayList()
    private var recomendedList: ArrayList<Data> = ArrayList()
    private var wifiList: ArrayList<Data> = ArrayList()
    private var shubList: ArrayList<Data> = ArrayList()
//    private var ropNewList: ArrayList<com.syarifhidayatullah.myapplication.model.response.rop.Data> = ArrayList()
//    private var ropPopList: ArrayList<com.syarifhidayatullah.myapplication.model.response.rop.Data> = ArrayList()
//    private var ropRecList: ArrayList<com.syarifhidayatullah.myapplication.model.response.rop.Data> = ArrayList()
//    private var ropWifiList: ArrayList<com.syarifhidayatullah.myapplication.model.response.rop.Data> = ArrayList()
//    private var ropShubList: ArrayList<com.syarifhidayatullah.myapplication.model.response.rop.Data> = ArrayList()

    //      private var footList: ArrayList<HomeModel> = ArrayList()
    private var kategori: ArrayList<String> = ArrayList()
    private lateinit var presenter: HomePresenter
    var progressDialog: Dialog? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()

        presenter = HomePresenter(this)
        presenter.getHome()

    }


    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        var user = Dashnet.getApp().getUser()
        var userResponse = Gson().fromJson(user, User::class.java)

        if (!userResponse.profil_picture.isNullOrEmpty()) {
            Glide.with(requireActivity())
                .load(userResponse.profil_picture)
                .apply(RequestOptions.circleCropTransform())
                .into(imvProfil)
        }
        if (userResponse.level.equals("admin")||userResponse.level.equals("kabag_teknisi")) {
            fb_add.visibility=View.GONE
        }
        fb_add.setOnClickListener {
            val addProduk = Intent(activity, AddProdukActivity::class.java)
            startActivity(addProduk)
        }
    }

//        fun initDataDummy() {
//            footList = ArrayList()
//            footList.add(HomeModel("Cherry ", "", 5f))
//            footList.add(HomeModel("Router ", "", 4.5f))
//            footList.add(HomeModel("Cherry Healthy", "", 4.4f))
//            footList.add(HomeModel("Cherry Healthy", "", 5f))
//        }

    override fun onClick(v: View, data: Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
//        val detail = Intent(activity, ReportActivity::class.java)
//        startActivity(detail)

//Toast.makeText(context,""+data.nama_produk,Toast.LENGTH_LONG).show()
    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {

        for (a in homeResponse.data.indices) {
            var items: List<String> =
                homeResponse.data[a].kategori?.nama_kategori.split(",") ?: ArrayList()

            for (x in items.indices) {
                 if (items[x].equals("radio", true)) {
                    newTasteList?.add(homeResponse.data[a])
                } else if (items[x].equals("modem", true)) {
                    recomendedList?.add(homeResponse.data[a])
                } else if (items[x].equals("router", true)) {
                    popularList?.add(homeResponse.data[a])
                } else if (items[x].equals("wifi", true)) {
                     wifiList?.add(homeResponse.data[a])
                 }else if (items[x].equals("switch-hub", true)) {
                     shubList?.add(homeResponse.data[a])
                 }

            }
        }

        var adapter = HomeAdapter(homeResponse.data, this)
        var layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rcvFoodList.layoutManager = layoutManager
        rcvFoodList.adapter = adapter


        val sectionPagerAdapter = SectionPagerAdapter(
            childFragmentManager
        )
        sectionPagerAdapter.setData(newTasteList, popularList, recomendedList, wifiList,shubList)
        viewPager.adapter = sectionPagerAdapter
        tablayout.setupWithViewPager(viewPager)
//        tablayout.setTabTextColors(ContextCompat.getColor(requireContext(),R.color.colorAccent),ContextCompat.getColor(requireContext(),R.color.colorPrimary))
    }

    override fun onHomeFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

//    override fun onRopSuccess(rop: List<com.syarifhidayatullah.myapplication.model.response.rop.Data>) {
//       for(a in rop.indices){
//           var items: List<String> =
//               rop[a].kategori.split(",") ?: ArrayList()
//
//           for (x in items.indices) {
//               if (items[x].equals("radio", true)) {
//                   ropNewList?.add(rop[a])
//               } else if (items[x].equals("modem", true)) {
//                   ropRecList?.add(rop[a])
//               } else if (items[x].equals("router", true)) {
//                   ropPopList?.add(rop[a])
//               } else if (items[x].equals("wifi", true)) {
//                   ropWifiList?.add(rop[a])
//               }else if (items[x].equals("switch-hub", true)) {
//                   ropShubList?.add(rop[a])
//               }
//
//           }
//       }
//        val sectionPagerAdapter = SectionPagerAdapter(
//            childFragmentManager
//        )
//        sectionPagerAdapter.setData(ropNewList, ropPopList, ropRecList, ropWifiList,ropShubList)
//        viewPager.adapter = sectionPagerAdapter
//        tablayout.setupWithViewPager(viewPager)
//    }

//    override fun onRopFailed(message: String) {
//
//    }


    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }
}