package com.syarifhidayatullah.myapplication.ui.order.inprogress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.dummy.HomeNewTasteModel
import com.syarifhidayatullah.myapplication.model.response.transaction.Data
import com.syarifhidayatullah.myapplication.utils.Helpers.converLongToTime
import kotlinx.android.synthetic.main.item_order_inprogress.view.*

class InProgressAdapter (
    private val listData:List<Data>,
    private val itemAdapterCallback:ItemAdapterCallback
):RecyclerView.Adapter<InProgressAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InProgressAdapter.ViewHolder {
val layoutInflater=LayoutInflater.from(parent.context)
        val view=layoutInflater.inflate(R.layout.item_order_inprogress,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
return  listData.size
    }

    override fun onBindViewHolder(holder: InProgressAdapter.ViewHolder, position: Int) {
      holder.bind(listData[position],itemAdapterCallback)
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(data: Data,itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                txvNama.text=data.teknisi.name
                txvPrice.text=data.kd_transaksi
//                txvPrice.formatPrice(data.food.price.toString())
              //  txvStatus.text=data.kd_transaksi
                txvStatus.text=data.createdAt?.converLongToTime("MMM dd , HH.mm" )
                Glide.with(context)
                    .load(data.teknisi.profil_picture)
                    .into(imvFood)
                itemView.setOnClickListener { itemAdapterCallback.onClick(it,data)  }
            }
        }
    }
interface ItemAdapterCallback{
    fun onClick(v: View, data:Data)
}}