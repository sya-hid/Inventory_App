package com.syarifhidayatullah.myapplication.ui.order.pastorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.transaksi_masuk.Data
import com.syarifhidayatullah.myapplication.utils.Helpers.converLongToTime
import kotlinx.android.synthetic.main.layout_row.view.*

class PastOrderAdapter (
    private val listData:List<Data>,
    private val itemAdapterCallback:ItemAdapterCallback
):RecyclerView.Adapter<PastOrderAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastOrderAdapter.ViewHolder {
val layoutInflater=LayoutInflater.from(parent.context)
        val view=layoutInflater.inflate(R.layout.layout_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
return  listData.size
    }

    override fun onBindViewHolder(holder: PastOrderAdapter.ViewHolder, position: Int) {
      holder.bind(listData[position],itemAdapterCallback)
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback){
            var no=1
            itemView.apply {
                no++
                idnotabel.text= data.kdTransaksiMasuk.toString()
                idnamaperusahaantabel.text=data.user.name

                idpemasukantabel.text=data.produk.namaProduk

                itemView.setOnClickListener { itemAdapterCallback.onClick(it,data)  }

            }
//            no++
        }
    }
interface ItemAdapterCallback{
    fun onClick(v: View, data:Data)

}}