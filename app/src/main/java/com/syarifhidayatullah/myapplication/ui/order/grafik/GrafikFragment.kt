package com.syarifhidayatullah.myapplication.ui.order.grafik

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.grafik.Data
import java.util.*
import kotlin.collections.ArrayList


class GrafikFragment : Fragment(), GrafikContract.View {
    //    var pastOrderList:ArrayList<Data>? = ArrayList()
    lateinit var presenter: GrafikPresenter

  //  data class Produk(val name: String, val tgl: String, val jumlah: Int)

//    var progressDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_report, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter = GrafikPresenter(this)
        presenter.getGrafik()

//        initView()
    }

    override fun onGrafikSuccess(dataGrafik: List<Data>) {
        val pie = AnyChart.column3d()
        val data: MutableList<DataEntry> = ArrayList()
        data.clear()
        for (a in dataGrafik.indices) {
            data.add(ValueDataEntry(dataGrafik[a].nama, dataGrafik[a].jumlah))
        }
        pie.data(data)
        val anyChartView: AnyChartView =
            Objects.requireNonNull(requireActivity()).findViewById(R.id.any_chart_view1)
        anyChartView.setChart(pie)

    }

//    private fun initView() {
//        progressDialog = Dialog(requireContext())
//        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
//        progressDialog?.let {
//            it.setContentView(dialogLayout)
//            it.setCancelable(false)
//            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
//        }
//    }

    override fun onGrafikFailed(message: String) {

    }

    override fun showLoading() {
//        progressDialog?.show()
    }

    override fun dismissLoading() {
//        progressDialog?.dismiss()
    }


}