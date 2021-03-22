package com.syarifhidayatullah.myapplication.ui.order.detail_masuk

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.transaksi_masuk.Data
import com.syarifhidayatullah.myapplication.utils.Cons
import com.syarifhidayatullah.myapplication.utils.Helpers.converLongToTime
import kotlinx.android.synthetic.main.fragment_detail_masuk.*

class DetailMasukFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_masuk, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var data = arguments?.getParcelable<Data>("data")
        initView(data)
        (activity as DetailMasukActivity?)!!.toolbarDetailMasuk()

    }

    private fun initView(data: Data?) {
        txvKodeProduk.text=data?.kdProduk
        tvNIK.text=data?.nikPetugas
        txvTanggal.text=data?.createdAt
        txvJumlahMasuk.text=data?.jumlah
        txvKeterangan.text=data?.keterangan
        Glide.with(requireActivity())
            .load(data?.user?.profilPicture)
            .into(ivPetugas)
        tvNikPetugas.text=data?.user?.nikPetugas
tvWaktu.text=data?.user?.createdAt?.converLongToTime(Cons.DATE_FORMAT_CHECKIN)
        txvPetugasGudang.text=data?.user?.name
        txvEmailPetugas.text=data?.user?.email
        txvPhoneNumber.text=data?.user?.phoneNumber

        Glide.with(requireActivity())
            .load(data?.produk?.gambarProduk)
            .into(ivProduk)
        tvKdProduk.text=data?.produk?.kdProduk
        tvNamaProduk.text=data?.produk?.namaProduk
        tvStok.text=data?.produk?.stok+" Unit"
        tvWaktu1.text=data?.produk?.createdAt?.converLongToTime(Cons.DATE_FORMAT_CHECKIN)
    }

}