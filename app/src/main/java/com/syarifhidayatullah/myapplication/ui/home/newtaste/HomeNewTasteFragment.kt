package com.syarifhidayatullah.myapplication.ui.home.newtaste

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.rop.Data

import com.syarifhidayatullah.myapplication.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_home_new_taste.*


class HomeNewTasteFragment : Fragment(), HomeNewtasteAdapter.ItemAdapterCallback,HomeTransContract.View{
  //    private var footList: ArrayList<HomeNewTasteModel> = ArrayList()
    private var newTasteList: ArrayList<com.syarifhidayatullah.myapplication.model.response.home.Data> = ArrayList()
    private var ropNewList: ArrayList<com.syarifhidayatullah.myapplication.model.response.rop.DataX> = ArrayList()
    private var ropPopList: ArrayList<com.syarifhidayatullah.myapplication.model.response.rop.DataX> = ArrayList()
    private var ropRecList: ArrayList<com.syarifhidayatullah.myapplication.model.response.rop.DataX> = ArrayList()
    private var ropWifiList: ArrayList<com.syarifhidayatullah.myapplication.model.response.rop.DataX> = ArrayList()
    private var ropShubList: ArrayList<com.syarifhidayatullah.myapplication.model.response.rop.DataX> = ArrayList()
    private lateinit var presenterReport:HomeTransPresenter
   // data class dataTrans(val nama_produk:String,val jumlah:Int, val tgl:String)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root=inflater.inflate(R.layout.fragment_home_new_taste, container, false)
        return root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)
        presenterReport=HomeTransPresenter(this)
        presenterReport.getRop()

        newTasteList = arguments?.getParcelableArrayList("data")!!

        var adapter = HomeNewtasteAdapter(newTasteList!!,this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)

        rcvFoodList.layoutManager = layoutManager
        rcvFoodList.adapter = adapter


        Toast.makeText(activity, "Kategori  : "+        newTasteList[1].kategori.nama_kategori, Toast.LENGTH_LONG).show()
//

//        ropNewList[1].stok
    }


    override fun onClick(v: View, data: com.syarifhidayatullah.myapplication.model.response.home.Data) {
        val detail = Intent(activity, DetailActivity::class.java).putExtra("data", data)
        startActivity(detail)
//        Toast.makeText(context,""+data.nama_produk, Toast.LENGTH_LONG).show()
    }

    override fun onRopSuccess(rop: Data) {

        for (a in rop.data.indices) {

            var items: List<String> = rop.data[a].kategori.split(",") ?: ArrayList()
            for (x in items.indices) {
//                Toast.makeText(activity, "stok      :   "+       rop.data[x].stok.toString()+"rop       : "+rop.data[x].rop.toString(), Toast.LENGTH_LONG).show()
                if (rop.data[x].stok <= rop.data[x].rop){
                    fb_add.visibility=View.GONE
                }else{
                    fb_add.visibility=View.VISIBLE
                }
                if (items[x].equals("radio", true)) {
                    ropNewList?.add(rop.data[a])
                    Toast.makeText(activity, ropNewList[x].rop, Toast.LENGTH_LONG).show()
                } else if (items[x].equals("modem", true)) {
                    ropRecList?.add(rop.data[a])
                    Toast.makeText(activity, ropRecList[x].rop, Toast.LENGTH_LONG).show()
                } else if (items[x].equals("router", true)) {
                    ropPopList?.add(rop.data[a])
                    Toast.makeText(activity, ropPopList[x].rop, Toast.LENGTH_LONG).show()
                } else if (items[x].equals("wifi", true)) {
                    ropWifiList?.add(rop.data[a])
                    Toast.makeText(activity, ropWifiList[x].rop, Toast.LENGTH_LONG).show()
                } else if (items[x].equals("switch-hub", true)) {
                    ropShubList?.add(rop.data[a])
                    Toast.makeText(activity, ropShubList[x].rop, Toast.LENGTH_LONG).show()
                }

            }



    }


    }
    override fun onRopFailed(message: String) {
//        Toast.makeText(activity, "gagal"+message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {

    }

    override fun dismissLoading() {

    }

}