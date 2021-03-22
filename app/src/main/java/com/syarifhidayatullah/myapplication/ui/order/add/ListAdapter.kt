package com.syarifhidayatullah.myapplication.ui.order.add

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.dummy.ListModel
import com.syarifhidayatullah.myapplication.model.response.keranjang.Data
import kotlinx.android.synthetic.main.item_list.view.*

class ListAdapter(
    private var listData: List<Data>,
//    private val listData: List<Data>,
//    var context: Context,
//    var keranjang: ArrayList<Data>,
    private val itemAdapterCallback: ItemAdapterCallback
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        holder.bind(listData[position], itemAdapterCallback)
        holder.itemView.btn_delete.setOnClickListener {
            itemAdapterCallback.onClick(listData[position],"delete")
//            removeCart(position)
        }
//        holder.itemView.btn_add.setOnClickListener {
//            val jumlah = holder.itemView.txvJumlah.text.toString()
//
//            val total = jumlah.toInt() + 1
//            holder.itemView.txvJumlah.text = total.toString()
//
//            itemAdapterCallback.onClick(listData[position],position,"update")
//        }
//        holder.itemView.btn_min.setOnClickListener {
//            val jumlah = holder.itemView.txvJumlah.text.toString()
//
//            val total = jumlah.toInt() - 1
//            holder.itemView.txvJumlah.text = total.toString()
//            itemAdapterCallback.onClick(listData[position],position,"update")
//        }
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Data, itemAdapterCallback: ItemAdapterCallback) {
            itemView.apply {
                txvName.text = data.produk.stok.toString()
                txvJenis.text = data.produk.nama_produk
                txvJumlah.text = data.jumlah.toString()
                Glide.with(context)
                    .load(data.produk.gambar_produk)
                    .into(imvFood)
cekJumlah()

                btn_min.setOnClickListener {
                    val jumlah = txvJumlah.text.toString()
                    val total = jumlah.toInt() - 1
                    txvJumlah.text = total.toString()
                    cekJumlah()
                    itemAdapterCallback.onClick(data,"min")


                }
                btn_add.setOnClickListener {
                    val jumlah = txvJumlah.text.toString()
                    val total = jumlah.toInt() + 1
                    txvJumlah.text = total.toString()
                    cekJumlah()
                    itemAdapterCallback.onClick(data,"add")
                }
//btn_delete.setOnClickListener {
//    itemAdapterCallback.onClick(data,"delete")
//    removeCart(position)
//}

//                itemView.setOnClickListener { itemAdapterCallback.onClick(it, data) }
            }

        }

        private fun cekJumlah() {
            val jlh = itemView.txvJumlah.text.toString()
            val stok = itemView.txvName.text.toString()

            if (jlh.toInt() < 2) {
                itemView.btn_min.visibility = View.INVISIBLE
//                itemView.btn_add.visibility = View.VISIBLE
            }
//            if (jlh.toInt() > 1) {
//                itemView.btn_min.visibility = View.VISIBLE
//                itemView.btn_add.visibility = View.INVISIBLE
//            }
            if(jlh.toInt() >= stok.toInt() ){
                itemView.btn_add.visibility=View.INVISIBLE
//                itemView.btn_min.visibility=View.VISIBLE
            }
        }
    }


//    fun setData(new:List<Data>){
//        listData.clear()
//        keranjang.addAll(new)
//        notifyDataSetChanged()
//    }
//    fun removeCart(position: Int) {
//        keranjang.removeAt(position)
//        keranjang.removeAt(position)
//        notifyItemRemoved(position)
//        notifyItemChanged(position, keranjang.size)
//
//    }
    interface ItemAdapterCallback {
        fun onClick(data: Data, type:String)
    }
}