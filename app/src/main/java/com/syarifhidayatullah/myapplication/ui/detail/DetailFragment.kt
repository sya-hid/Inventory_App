package com.syarifhidayatullah.myapplication.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.home.Data
import com.syarifhidayatullah.myapplication.ui.MainActivity
import com.syarifhidayatullah.myapplication.utils.Helpers.formatPrice
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    var bundle: Bundle? = null
    var data: Data? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarPayment()
        var data = arguments?.getParcelable<Data>("data")
        initView(data)
//        arguments?.let {
//            DetailFragmentArgs.fromBundle(it).data.let {
//                initView(it)
//            }
//        }
//        btnOrderNow.setOnClickListener {view ->
//            Navigation.findNavController(view).navigate(R.id.action_payment, bundle)
//        }
        imageView3.setOnClickListener {
            getActivity()?.finish();
        }
    }

    private fun initView(data: Data?) {
//        Toast.makeText(context, ""+data?.nama_produk, Toast.LENGTH_SHORT).show()
        Glide.with(requireActivity())
            .load(data?.gambar_produk)
            .into(imageView2)
        txvDesc.text = data?.kd_produk
        txvDesc2.text = data?.nama_produk
       txvIngredient.text = data?.kategori?.nama_kategori
        txvPrice.text=data?.stok.toString()+" Unit"
//        if (data != null) {
//            txvPrice.formatPrice(data.price.toString())
//        }
        bundle = bundleOf("data" to data)
    }

}