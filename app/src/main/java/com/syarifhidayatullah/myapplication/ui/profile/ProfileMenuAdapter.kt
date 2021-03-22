package com.syarifhidayatullah.myapplication.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.dummy.ProfileMenuModel
import kotlinx.android.synthetic.main.item_menu_profile.view.*

class ProfileMenuAdapter (
    private val listData:List<ProfileMenuModel>,
    private val itemAdapterCallback:ItemAdapterCallback
):RecyclerView.Adapter<ProfileMenuAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileMenuAdapter.ViewHolder {
val layoutInflater=LayoutInflater.from(parent.context)
        val view=layoutInflater.inflate(R.layout.item_menu_profile,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
return  listData.size
    }

    override fun onBindViewHolder(holder: ProfileMenuAdapter.ViewHolder, position: Int) {
      holder.bind(listData[position],itemAdapterCallback)
    }
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(data: ProfileMenuModel,itemAdapterCallback: ItemAdapterCallback){
            itemView.apply {
                txvmenu.text=data.title
                ///rbFood.rating=data.rating
//                Glide.with(context)
//                    .load(data.src)
//                    .into(imvPoster1)
                itemView.setOnClickListener { itemAdapterCallback.onClick(it,data)  }
            }
        }
    }
interface ItemAdapterCallback{
    fun onClick(v: View, data:ProfileMenuModel)
}}