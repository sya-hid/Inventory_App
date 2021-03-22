package com.syarifhidayatullah.myapplication.ui.order.detail

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.graphics.convertTo
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.transaction.Data
import com.syarifhidayatullah.myapplication.model.response.transaction_detail.TransactionDetailResponse
import com.syarifhidayatullah.myapplication.utils.Cons
import com.syarifhidayatullah.myapplication.utils.Helpers.converLongToTime
//import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail_peminjaman.*


class DetailPeminjamanFragment : Fragment(),ListDetailAdapter.ItemAdapterCallback,DetailPeminjamanContract.View{

    var progressDialog: Dialog? = null
    lateinit var presenter : DetailPeminjamanPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_peminjaman, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        arguments?.let {
//            DetailPeminjamanFragmentArgs.fromBundle(it).data.let {
//                initView(it)
//            }
//        }
        initView()
        presenter = DetailPeminjamanPresenter(this)
        var data = arguments?.getParcelable<Data>("data")
        presenter.getDetailTransaksi(data?.kd_transaksi.toString())
        initView(data)
        (activity as DetailPeminjamanActivity?)!!.toolbarDetailPeminjaman()



    }

    private fun initView(data: Data?) {
        Glide.with(requireActivity())
            .load(data?.teknisi?.profil_picture)
            .into(ivPoster)
        tvTitle.text=data?.kd_transaksi
        tvPrice.text= data?.createdAt?.converLongToTime(Cons.DATE_FORMAT_CHECKIN)
//        txvPrice.visibility=
        txvPetugasGudang.text =data?.gudang?.name
        txvPetugasTeknisi.text=data?.teknisi?.name
txvPekerjaan.text=data?.jenis_pekerjaan
        txvLokasi.text=data?.lokasi_pekerjaan


    }

private fun initView(){
    progressDialog = Dialog(requireContext())
    val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

    progressDialog?.let {
        it.setContentView(dialogLayout)
        it.setCancelable(false)
        it.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}




    override fun onUpdateTransactionSuccess(
        transactionDetailResponse: TransactionDetailResponse
    ) {
//       Toast.makeText(context, "Sukses", Toast.LENGTH_SHORT).show()
        var adapter=ListDetailAdapter(transactionDetailResponse.data, this)
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        rcvList.layoutManager=layoutManager
        rcvList.adapter=adapter
    }

    override fun onUpdateTransactionFailed(message: String) {
      //  Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    override fun onClick(
        v: View,
        data: com.syarifhidayatullah.myapplication.model.response.transaction_detail.Data
    ) {
        Toast.makeText(context, ""+data.produk.nama_produk, Toast.LENGTH_SHORT).show()
    }

}