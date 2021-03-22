package com.syarifhidayatullah.myapplication.ui.profile

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.syarifhidayatullah.myapplication.ui.profile.account.ProfileAccountFragment
import com.syarifhidayatullah.myapplication.ui.profile.foodmarket.ProfileFoodMarketFragment

class SectionPagerAdapter (fm:FragmentManager) : FragmentPagerAdapter(fm){

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Account"
            1 -> "Dashnet"
            else->""
        }
    }
    override fun getItem(position: Int): Fragment {
        var fragment:Fragment
        return when(position){
                0 ->{
                        fragment = ProfileAccountFragment()
                return fragment
            }
            1 ->{
                fragment = ProfileFoodMarketFragment()
                return fragment
            }
            else
                ->{
                fragment=ProfileAccountFragment()
                return fragment
            }

        }
    }

    override fun getCount(): Int {
return 2
    }
}