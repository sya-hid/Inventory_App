package com.syarifhidayatullah.myapplication.ui.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.syarifhidayatullah.myapplication.model.response.transaction.Data
import com.syarifhidayatullah.myapplication.ui.order.grafik.GrafikFragment
import com.syarifhidayatullah.myapplication.ui.order.inprogress.InProgressFragment
import com.syarifhidayatullah.myapplication.ui.order.pastorder.PastOrderFragment
import com.syarifhidayatullah.myapplication.ui.order.pastorder.ReportFragment


class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    var inProgressList: ArrayList<Data>? = ArrayList()
    var pastOrderList: ArrayList<Data>? = ArrayList()

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Hari Ini"
            1 -> "Masuk"
            2 -> "Keluar"
            3 -> "Grafik"
            else -> ""
        }
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment
        return when (position) {
            0 -> {
                fragment = InProgressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inProgressList)
                fragment.arguments = bundle
                return fragment

            }
            1 -> {
                fragment = PastOrderFragment()
//                val bundle = Bundle()
//                bundle.putParcelableArrayList("data", pastOrderList)
//                fragment.arguments = bundle
                return fragment
            }
            2 -> {
                fragment = ReportFragment()
//                val bundle = Bundle()
//                bundle.putParcelableArrayList("data", pastOrderList)
//                fragment.arguments = bundle
                return fragment
            }
           3 -> {
                fragment = GrafikFragment()
//                val bundle = Bundle()
//                bundle.putParcelableArrayList("data", pastOrderList)
//                fragment.arguments = bundle
                return fragment
            }
            else
            -> {
                fragment = InProgressFragment()
                val bundle = Bundle()
                bundle.putParcelableArrayList("data", inProgressList)
                fragment.arguments = bundle
                return fragment
            }

        }
    }

    override fun getCount(): Int {
        return 4
    }

    fun setData(inProgressListParms: ArrayList<Data>?, pastOrderListParms: ArrayList<Data>?) {
        inProgressList = inProgressListParms
        pastOrderList = pastOrderListParms
    }
}