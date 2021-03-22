package com.syarifhidayatullah.myapplication.ui.produk

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.zxing.Result
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.model.response.home.HomeResponse
import com.syarifhidayatullah.myapplication.model.response.kategori.KategoriResponse
import com.syarifhidayatullah.myapplication.utils.FileUtils
import kotlinx.android.synthetic.main.activity_add_produk.*
import kotlinx.android.synthetic.main.activity_add_produk.frame_layout_camera
import kotlinx.android.synthetic.main.activity_trans.*
import me.dm7.barcodescanner.core.IViewFinder
import me.dm7.barcodescanner.zxing.ZXingScannerView
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class AddProdukActivity : AppCompatActivity(), AddProdukContract.View,
    ZXingScannerView.ResultHandler, View.OnClickListener {

    private lateinit var presenter: AddProdukPresenter
    private lateinit var mScannerView: ZXingScannerView
    var addFoto: Boolean = false
    private var filePath: Uri? = null
    var progressDialog: Dialog? = null

    private val Keterangan = arrayOf("Pengembalian", "Pembelian","Cabut Alat")
    private  var kategori: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_produk)
        presenter = AddProdukPresenter(this)
        presenter.getKategori()
        edtKodeBarang.setFocusable(false)
        edtKodeBarang.setInputType(InputType.TYPE_NULL)
        initScannerView()
        initListener()
        initView()

        val adapterKeterangan: ArrayAdapter<String> = ArrayAdapter<String>(
        this, android.R.layout.simple_spinner_dropdown_item, Keterangan)
        spnKeterangan.setAdapter(adapterKeterangan)
    }

    private fun initView() {
        progressDialog = Dialog(this)
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            addFoto = true
            filePath = data?.data!!
            Glide.with(this)
                .load(filePath)
                .apply(RequestOptions.circleCropTransform())
                .into(imvProfil)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "task cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListener() {
        imvProfil.setOnClickListener {
            if (addFoto) {
                addFoto = false
                imvProfil.setImageResource(R.drawable.ic_picture_empty)
            } else {
                ImagePicker.with(this).cameraOnly().start()
            }
        }
        btnContinue.setOnClickListener {
            val gambar_produk = FileUtils.getFile(this,filePath)
            if (filePath!=null){
                saveBitmapToFile(gambar_produk)
            }
            val kode_barang=edtKodeBarang.text.toString()
            val kategori:String=spnKategori.selectedItem.toString()
            val kode_kategori: String = kategori.substring(0, 1)
//            val kode_kategori=spnKategori.selectedItem.toString()
            val nama_barang=edtNamaBarang.text.toString()
            val jumlah:Int=edtJumlah.text.toString().toInt()
            val keterangan=spnKeterangan.selectedItem.toString()
            if (kode_barang.isNullOrEmpty()){
                edtKodeBarang.error="Scan Barang"
                edtKodeBarang.requestFocus()
            }else if (nama_barang.isNullOrEmpty()){
            edtNamaBarang.error="Masukkan Nama Barang"
            edtNamaBarang.requestFocus()
        }else  if (jumlah==null){
                edtJumlah.error="Masukkan Jumlah Barang"
                edtJumlah.requestFocus()
            }       else{
            presenter.add_produk(kode_barang,kode_kategori,nama_barang,gambar_produk,jumlah,keterangan)
            }
//            Toast.makeText(this, edtKodeBarang.text.toString() + edtNamaBarang.text.toString() + edtJumlah.text.toString() +kode_kategori + spnKeterangan.selectedItem.toString()+gambar_produk,Toast.LENGTH_SHORT).show()
        }
    }

    private fun doRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            100 -> {
                initScannerView()
            }
            else -> {
                /* nothing to do in here */
            }
        }
    }

    private fun initScannerView() {
        mScannerView = object : ZXingScannerView(this) {
            override fun createViewFinderView(context: Context?): IViewFinder {
                return super.createViewFinderView(context!!)
            }
        }
        mScannerView.setAutoFocus(true)
        mScannerView.setResultHandler(this)
        frame_layout_camera.addView(mScannerView)
    }

    override fun onHomeSuccess(homeResponse: HomeResponse) {
        for(a in homeResponse.data.indices){
            imvProfil.isClickable=false
            edtNamaBarang.setFocusable(false)
            for (a in homeResponse.data.indices) {
//                edtKodeBarang.setText(homeResponse.data[a].kd_produk)
                edtNamaBarang.setText(homeResponse.data[a].nama_produk)

                Glide.with(this)
                    .load(homeResponse.data[a].gambar_produk)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imvProfil)
                kategori.clear()
                kategori.add(homeResponse.data[a].kd_kategori+" - "+homeResponse.data[a].kategori.nama_kategori)

            }
            imvProfil.isClickable=true
            edtNamaBarang.setFocusable(true)
        }

        val adapterNama: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, kategori)
        spnKategori.adapter = adapterNama

    }
    override fun onHomeFailed(message: String) {

   }
    fun saveBitmapToFile(file: File): File? {
        return try {

            // BitmapFactory options to downsize the image
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            o.inSampleSize = 6
            // factor of downsizing the image
            var inputStream = FileInputStream(file)
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o)
            inputStream.close()

            // The new size we want to scale to
            val REQUIRED_SIZE = 75

            // Find the correct scale value. It should be the power of 2.
            var scale = 1
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                o.outHeight / scale / 2 >= REQUIRED_SIZE
            ) {
                scale *= 2
            }
            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            inputStream = FileInputStream(file)
            val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2)
            inputStream.close()

            // here i override the original image file
            file.createNewFile()
            val outputStream = FileOutputStream(file)
            selectedBitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            file
        } catch (e: Exception) {
            null
        }
    }

    override fun onKategoriSuccess(kategoriResponse: KategoriResponse) {

        for (a in kategoriResponse.data.indices) {
kategori.add(kategoriResponse.data[a].kd_kategori+" - "+kategoriResponse.data[a].nama_kategori)

        }
        val adapterNama: ArrayAdapter<String> =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, kategori)
        spnKategori.adapter = adapterNama
        //  spinner.setAdapter(adapterNama)
    }

    override fun onKategoriFailed(message: String) {
        Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show()    }

    override fun onAddProdukSuccess(produkResponse: HomeResponse) {
        Toast.makeText(this, "Transaksi Sukses", Toast.LENGTH_SHORT).show()
     finish()
    }

    override fun onAddProdukFailed(message: String) {
finish()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.edtKodeBarang -> {
//                presenter.getHome("89927791609061")
                currentFocus
                initScannerView()
                linearAddProduk.visibility = View.GONE
                frame_layout_camera.visibility = View.VISIBLE
                mScannerView.startCamera()
                doRequestPermission()
            }
            else -> {

            }
        }
    }

    override fun onPause() {
        mScannerView.stopCamera()
        super.onPause()
    }

    override fun onStart() {
        super.onStart()

        mScannerView.startCamera()
        doRequestPermission()
    }

    override fun handleResult(p0: Result?) {
        edtKodeBarang.setText(p0?.text)
        presenter.getHome(p0.toString())
        frame_layout_camera.visibility = View.GONE
        linearAddProduk.visibility = View.VISIBLE
    }
}