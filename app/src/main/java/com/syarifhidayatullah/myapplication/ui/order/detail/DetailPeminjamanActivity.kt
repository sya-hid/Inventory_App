package com.syarifhidayatullah.myapplication.ui.order.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.navigation.Navigation
import com.syarifhidayatullah.myapplication.R
import kotlinx.android.synthetic.main.layout_toolbar.*

class DetailPeminjamanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_peminjaman)

        intent.extras?.let {
            val navController = Navigation.findNavController(findViewById(R.id.detailPeminjamanHostFragment))
            val bundle = Bundle()
            bundle.putParcelable("data", it.get("data") as Parcelable?)
            navController.setGraph(navController.graph, bundle)
        }
    }
    fun toolbarDetailPeminjaman() {
        toolbar.visibility = View.VISIBLE
        toolbar.title = "Transaction"
        toolbar.subtitle = "Transaction details"
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_arrow_back_000)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}