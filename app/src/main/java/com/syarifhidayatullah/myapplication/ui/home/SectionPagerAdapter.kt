package com.syarifhidayatullah.myapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

import com.syarifhidayatullah.myapplication.model.response.home.Data
import com.syarifhidayatullah.myapplication.ui.home.newtaste.HomeNewTasteFragment

class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var newTasteList: ArrayList<Data>? = ArrayList()
    var popularList: ArrayList<Data>? = ArrayList()
    var recomendedList: ArrayList<Data>? = ArrayList()
    var wifiList: ArrayList<Data>? = ArrayList()
    var shubList: ArrayList<Data>? = ArrayList()

    var ropNewList: ArrayList<com.syarifhidayatullah.myapplication.model.response.rop.Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Radio"
            1 -> "Router"
            2 -> "Wifi"
            3 -> "Modem"
            4 -> "Switch-Hub"
            else -> ""
        }
    }

    override fun getCount(): Int {
        return 5
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment
        return when (position) {
            0 -> {
                fragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", newTasteList)
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", popularList)
                fragment.arguments = bundle
                return fragment
            }
            2 -> {
                fragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", wifiList)
                fragment.arguments = bundle
                return fragment
            }
            3 -> {
                fragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", recomendedList)
                fragment.arguments = bundle
                return fragment
            }4 -> {
                fragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", shubList)
                fragment.arguments = bundle
                return fragment
            }
            else
            -> {
                fragment = HomeNewTasteFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", newTasteList)
                fragment.arguments = bundle
                return fragment
            }

        }

    }

    fun setData(
        newTasteListParms: ArrayList<Data>?,
        popularListParms: ArrayList<Data>?,
        recomendedListParms: ArrayList<Data>?,
        wifiListParms: ArrayList<Data>?,
        shubListParms: ArrayList<Data>?
    ) {
        newTasteList = newTasteListParms
        popularList = popularListParms
        recomendedList = recomendedListParms
        wifiList = wifiListParms
        shubList = shubListParms
    }

}