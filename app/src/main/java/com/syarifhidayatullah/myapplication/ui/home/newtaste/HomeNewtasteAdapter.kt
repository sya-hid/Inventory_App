package com.syarifhidayatullah.myapplication.ui.home.newtaste

import android.content.Context
import android.graphics.Color.red
import android.text.TextPaint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.anychart.chart.common.dataentry.DataEntry
import com.bumptech.glide.Glide
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.dummy.HomeModel
import com.syarifhidayatullah.myapplication.model.dummy.HomeNewTasteModel
import com.syarifhidayatullah.myapplication.model.dummy.HomedataTransModel
import com.syarifhidayatullah.myapplication.model.response.home.Data
import com.syarifhidayatullah.myapplication.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.item_home_vertical.view.*

class HomeNewtasteAdapter(
    private val listData: List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback
) : RecyclerView.Adapter<HomeNewtasteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeNewtasteAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_home_vertical, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: HomeNewtasteAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position],itemAdapterCallback)

//        val groupByname=listDataTrans.groupBy{it->it.nama_produk}
//        groupByname.forEach{println(it)}
//
//        val sumProdukTotalMap=groupByname.mapValues{(name,prod)->prod.sumBy{it.jumlah!!.toInt()}}
//        sumProdukTotalMap.forEach{println(it)
//        val data: MutableList<HomedataTransModel> = ArrayList()
//            data.add(HomedataTransModel(it.component1(),it.component2(),it.key))
//
//        }


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {

            itemView.apply {
                txvTittle.text = data.nama_produk
                txvPrice.text=data.stok.toString() + " unit"
                Glide.with(context)
                    .load(data.gambar_produk)
                    .into(imvPoster2)
                if (data.stok!!.toInt() <  1){
txvPrice.setTextColor(ContextCompat.getColor(context,R.color.colorPrimaryDark))
                }
                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }

            }
        }
    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data: Data)
    }
}