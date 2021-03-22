package com.syarifhidayatullah.myapplication.ui.detail

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.syarifhidayatullah.myapplication.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        intent.extras?.let {
            val navController = Navigation.findNavController(findViewById(R.id.detailHostFragment))
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable?)
            navController.setGraph(navController.graph, bundle)
                  }
    }

    fun toolbarPayment() {
        toolbar.visibility = View.GONE
        toolbar.title = "Payment"
        toolbar.subtitle = "You deserve better meal"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

//    fun toolbarDetail() {
//        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000, null)
//        toolbar.setNavigationOnClickListener { onBackPressed() }
//        toolbar.visibility = View.GONE
//    }
}