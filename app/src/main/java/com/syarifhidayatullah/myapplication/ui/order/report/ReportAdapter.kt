package com.syarifhidayatullah.myapplication.ui.order.pastorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.transaction.Data
import com.syarifhidayatullah.myapplication.utils.Helpers.converLongToTime
import kotlinx.android.synthetic.main.layout_row.view.*

class ReportAdapter (
    private val listData:List<Data>,
    private val itemAdapterCallback:ItemAdapterCallback
):RecyclerView.Adapter<ReportAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportAdapter.ViewHolder {
val layoutInflater=LayoutInflater.from(parent.context)
        val view=layoutInflater.inflate(R.layout.layout_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
return  listData.size
    }

    override fun onBindViewHolder(holder: ReportAdapter.ViewHolder, position: Int) {
      holder.bind(listData[position],itemAdapterCallback)
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(data: Data,itemAdapterCallback: ItemAdapterCallback){
            var no=1
            itemView.apply {
                no++
                idnotabel.text= data.kd_transaksi
                idnamaperusahaantabel.text=data.gudang.name

                idpemasukantabel.text=data.teknisi.name

                itemView.setOnClickListener { itemAdapterCallback.onClick(it,data)  }

            }
//            no++
        }
    }
interface ItemAdapterCallback{
    fun onClick(v: View, data:Data)
}}