package com.syarifhidayatullah.myapplication.ui.order.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.transaction_detail.Data
import kotlinx.android.synthetic.main.list_item.view.*

class ListDetailAdapter(
    private val listData: List<Data>,
    private val itemAdapterCallback: ItemAdapterCallback
) : RecyclerView.Adapter<ListDetailAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDetailAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ListDetailAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
//        holder.itemView.btn_add.setOnClickListener {
//
//        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {


            itemView.apply {
                textView23.text=data.produk.nama_produk
              tvAddress.text=data.jumlah


                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }

        }


    }

    interface ItemAdapterCallback {
        fun onClick(v: View, data: Data)
    }
}