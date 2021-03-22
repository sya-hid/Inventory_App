package com.syarifhidayatullah.myapplication.ui.order.add

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.zxing.Result
import com.syarifhidayatullah.myapplication.Dashnet
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.keranjang.Data
import com.syarifhidayatullah.myapplication.model.response.keranjang.KeranjangResponse
import com.syarifhidayatullah.myapplication.model.response.login.User
import com.syarifhidayatullah.myapplication.model.response.teknisi.TeknisiResponse
import kotlinx.android.synthetic.main.activity_trans.*
import me.dm7.barcodescanner.core.IViewFinder
import me.dm7.barcodescanner.zxing.ZXingScannerView


class TransActivity : AppCompatActivity(),ZXingScannerView.ResultHandler,ListAdapter.ItemAdapterCallback,TransContract.View {
    private lateinit var mScannerView: ZXingScannerView
    private var dataList:ArrayList<com.syarifhidayatullah.myapplication.model.response.keranjang.Data> = ArrayList()
    private lateinit var presenter: TransPresenter
//    private val spinner: Spinner = findViewById(R.id.spnNamaTeknisi)
//    private val spinner2: Spinner = findViewById(R.id.spnJenisPekerjaan)
//    private val Nama = arrayOf(
//        "Gudang 1", "Gudang 2", "Gudang 3", "Gudang 4"
//    )
    private val Pekerjaan = arrayOf(
         "Installasi", "Service"
    )
    private  var nama: ArrayList<String> = ArrayList()
    var progressDialog: Dialog? = null



    private lateinit var keranjangAdapter:ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trans)
        initScannerView()
        //initDummy()

initView()
        presenter= TransPresenter(this)
        presenter.getTeknisi()
        presenter.getKeranjang()


        val adapterJenis: ArrayAdapter<String> = ArrayAdapter<String>(
            this,android.R.layout.simple_spinner_dropdown_item, Pekerjaan
        )

        spnJenisPekerjaan.setAdapter(adapterJenis)

btnSimpan.setOnClickListener {
//    Toast.makeText(this, edtNamaTeknisi.text.toString()+dataList[0].name+dataList[0].jumlah, Toast.LENGTH_SHORT).show()
    var user= Dashnet.getApp().getUser()
    var userResponse= Gson().fromJson(user, User::class.java)
    val nama: Spinner = findViewById(R.id.spnNamaTeknisi)

    val jenis: Spinner = findViewById(R.id.spnJenisPekerjaan)
   val lokasi: EditText = findViewById(R.id.edtLokasi)
    val nik:String=nama.selectedItem.toString()
    val d: String = nik.substring(0, 9)
    if(edtLokasi.text.isNullOrEmpty()){
        edtLokasi.error="Silahkan isi Lokasi"
        edtLokasi.requestFocus()
    }
else {
        presenter.checkout(
            userResponse.nik_petugas,
            d,
            jenis.selectedItem.toString(),
            lokasi.text.toString()
        )
    }
}


    }

//    private fun initDummy() {
//        dataList= ArrayList()
//        dataList.add(Data("Radio","Bullet M5","",1))
//        dataList.add(Data("Wifi","TP-Link","",2))
//        dataList.add(Data("Router","Router Board 250 GS","",4))
//        dataList.add(Data("Modem","Fiberhome HG G2 BC","",3))
//    }

    private fun initScannerView() {
        mScannerView=object : ZXingScannerView(this){
            override fun createViewFinderView(context: Context?): IViewFinder {
                return super.createViewFinderView(context!!)
            }
        }
        mScannerView.setAutoFocus(true)
        mScannerView.setResultHandler(this)
        frame_layout_camera.addView(mScannerView)
    }

    private fun doRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
            }
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            100 -> {
                initScannerView()
            }
            else -> {
                /* nothing to do in here */
            }
        }
    }
    private fun initView(){
        progressDialog = Dialog(this)
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)

        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
    override fun onPause() {
        mScannerView.stopCamera()
        super.onPause()
    }
    override fun onStart() {
        mScannerView.startCamera()
        doRequestPermission()
        super.onStart()

    }
    override fun handleResult(p0: Result?) {
//        Toast.makeText(this, p0.toString(), Toast.LENGTH_SHORT).show()
        presenter.addCart(p0.toString(),"1")
        mScannerView.resumeCameraPreview(this)
//       onPause()
        onStart()
    }

    override fun onClick(data:Data, type:String) {

when (type){
    "delete" ->
        presenter.deleteItemCart(data.kd_keranjang)
    "add" ->{
        var Jlh:Int=data.jumlah.toInt() + 1
        presenter.updateCart(data.kd_keranjang,data.kd_produk,Jlh.toString())}
    "min" -> {
        var Jlh:Int=data.jumlah.toInt() - 1
        presenter.updateCart(data.kd_keranjang,data.kd_produk,Jlh.toString())}
}
    }
    override fun onTeknisiSuccess(teknisiResponse: TeknisiResponse) {


        for (a in teknisiResponse.data.indices) {
           nama.add(teknisiResponse.data[a].nik_petugas +" - "+teknisiResponse.data[a].name)

        }
        val adapterNama: ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, nama)
        //  spinner.setAdapter(adapterNama)

        spnNamaTeknisi.adapter=adapterNama

    }

    override fun onTeknisiFailed(message: String) {

    }

    override fun onKeranjangSuccess(keranjangResponse: KeranjangResponse) {
        val data:List<Data> = keranjangResponse.data
//        for(a in keranjangResponse.data.indices){
//            dataList.add(Data(keranjangResponse.data[a].kd_keranjang,keranjangResponse.data[a].nik_petugas,keranjangResponse.data[a].nik_petugas,keranjangResponse.data[a].jumlah,keranjangResponse.data[a].created_at,keranjangResponse.data[a].updated_at,keranjangResponse.data[a].produk))
//        }
        if (data.isEmpty()){
            btnSimpan.isClickable=false
        }else{
        btnSimpan.isClickable=true
        }

        btnSimpan.visibility= View.VISIBLE
        var adapter=ListAdapter(keranjangResponse.data, this)
        var layoutManager:RecyclerView.LayoutManager = LinearLayoutManager(this)
        rcvList.layoutManager=layoutManager
        rcvList.adapter=adapter
//        keranjangAdapter.setData(data)

    }

    override fun onKeranjangFailed(message: String) {
//        Toast.makeText(this, "data kosong", Toast.LENGTH_SHORT).show()
         }

    override fun onAddcartSuccess(keranjangResponse: KeranjangResponse) {
        presenter.getKeranjang()
        mScannerView.resumeCameraPreview(this)
        Toast.makeText(this, "Transaksi Sukses", Toast.LENGTH_SHORT).show()
    }



    override fun onAddCartFailed(message: String) {

    }

    override fun onUpdatecartSuccess(keranjangResponse: KeranjangResponse) {
     presenter.getKeranjang()
    }

    override fun onUpdateCartFailed(message: String) {

    }

    override fun onDeleteItemCartSuccess(keranjangResponse: KeranjangResponse) {
     presenter.getKeranjang()
    }

    override fun onDeleteItemCartFailed(message: String) {

    }

    override fun onCheckoutSuccess(message: String) {
        finish()
    }

    override fun onCheckoutFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
progressDialog?.show()
    }

    override fun dismissLoading() {
progressDialog?.dismiss()
    }


}