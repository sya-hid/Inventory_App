package com.syarifhidayatullah.myapplication.network

import com.syarifhidayatullah.myapplication.model.response.Wrapper
import com.syarifhidayatullah.myapplication.model.response.grafik.ListData
import com.syarifhidayatullah.myapplication.model.response.home.HomeResponse
import com.syarifhidayatullah.myapplication.model.response.kategori.KategoriResponse
import com.syarifhidayatullah.myapplication.model.response.keranjang.KeranjangResponse
import com.syarifhidayatullah.myapplication.model.response.login.LoginResponse
import com.syarifhidayatullah.myapplication.model.response.logout.LogoutResponse

import com.syarifhidayatullah.myapplication.model.response.teknisi.TeknisiResponse
import com.syarifhidayatullah.myapplication.model.response.transaction.TransactionResponse
import com.syarifhidayatullah.myapplication.model.response.transaction_detail.TransactionDetailResponse
import com.syarifhidayatullah.myapplication.model.response.transaksi_masuk.TransaksiMasukResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*
import java.util.*

interface Endpoint {
    @FormUrlEncoded
    @POST("login_pegawai")
    fun login(
        @Field("nik_petugas") email: String,
        @Field("password") password: String
    ): Observable<Wrapper<LoginResponse>>

    @Multipart
    @POST("update_profil")
    fun update_profil(
        @Query("nik") nik_petugas: String,
        @Query("password") password: String,
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("phone_number") phoneNumber: String,
        @Query("address") alamat: String,
        @Query("level") level: String,
        @Part profil_picture: MultipartBody.Part
    ): Observable<Wrapper<LoginResponse>>


    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("password_confirmation") password_confirmation: String,
        @Field("address") address: String,
        @Field("city") city: String,
        @Field("houseNumber") houseNumber: String,
        @Field("phoneNumber") phoneNumber: String
    ): Observable<Wrapper<LoginResponse>>

    @Multipart
    @POST("user/photo")
    fun registerPhoto(
        @Part profileImage: MultipartBody.Part
    ): Observable<Wrapper<Any>>


    @GET("get_produk")
    fun home(): Observable<Wrapper<HomeResponse>>

    @GET("get_kategori")
    fun get_kategori(): Observable<Wrapper<KategoriResponse>>

    @FormUrlEncoded
    @POST("get_produk_kode")
    fun get_produk_by_kode(
        @Field("kd_produk") kd_produk: String
    ): Observable<Wrapper<HomeResponse>>

    @Multipart
    @POST("add_produk_transaksi")
    fun add_produk(
        @Query("kd_produk") kd_produk: String,
        @Query("kd_kategori") kd_kategori: String,
        @Query("nama_produk") nama_produk: String,
        @Part gambar_produk: MultipartBody.Part,
        @Query("jumlah") jumlah: Int,
        @Query("keterangan") keterangan: String
    ): Observable<Wrapper<HomeResponse>>

    @GET("get_cart")
    fun get_keranjang(): Observable<Wrapper<KeranjangResponse>>

    @GET("get_teknisi")
    fun get_teknisi(): Observable<Wrapper<TeknisiResponse>>

    @GET("logout")
    fun logout(): Observable<Wrapper<LogoutResponse>>

    //     @FormUrlEncoded
//     @POST("checkout")
//     fun checkout(
//         @Field("food_id") foodId: String,
//         @Field("user_id") userId: String,
//         @Field("quantity") quantity: String,
//         @Field("total") total: String,
//         @Field("status") status: String
//     ): Observable<Wrapper<CheckoutResponse>>
    @FormUrlEncoded
    @POST("checkout")
    fun checkout(
        @Field("nik_petugas") nik_petugas: String,
        @Field("nik_teknisi") nik_teknisi: String,
        @Field("jenis_pekerjaan") jenis_pekerjaan: String,
        @Field("lokasi_pekerjaan") lokasi_pekerjaan: String
    ): Observable<Wrapper<Any>>

    @FormUrlEncoded
    @POST("add_cart")
    fun add_cart(

        @Field("kd_produk") kd_produk: String,
        @Field("jumlah") jumlah: String
    ): Observable<Wrapper<KeranjangResponse>>

    @FormUrlEncoded
    @POST("update_item_cart")
    fun update_cart(

        @Field("kd_keranjang") kd_keranjang: String,
        @Field("kd_produk") kd_produk: String,
        @Field("jumlah") jumlah: String
    ): Observable<Wrapper<KeranjangResponse>>

    @FormUrlEncoded
    @POST("delete_item_cart")
    fun delete_item_cart(
    @Field("kd_keranjang") kd_keranjang: String
    ): Observable<Wrapper<KeranjangResponse>>

    @FormUrlEncoded
    @POST("get_transaksi_bydate")
    fun get_transaksi_bydate(
        @Field("start_date") start_date: String,
        @Field("end_date") end_date: String
    ): Observable<Wrapper<TransactionResponse>>

    @GET("get_transaksi")
    fun get_transaksi(): Observable<Wrapper<TransactionResponse>>

    @GET("get_lap_transaksi_masuk")
    fun get_lap_transaksi_masuk(): Observable<Wrapper<TransaksiMasukResponse>>

    @FormUrlEncoded
    @POST("get_lap_transaksi")
    fun lap_transaksi(
        @Field("start_date") start_date: String,
        @Field("end_date") end_date: String
    ): Observable<Wrapper<TransaksiMasukResponse>>

    @FormUrlEncoded
    @POST("get_detail_transaksi")
    fun get_detail_transaksi(
        @Field("kd_transaksi") kd_transaksi: String
    ): Observable<Wrapper<TransactionDetailResponse>>

    @GET("get_data")
    fun get_rop(): Observable<com.syarifhidayatullah.myapplication.model.response.rop.DataRop>

    @GET("get_detail_transaksi_")
    fun get_detail_transaksi_(): Observable<Wrapper<TransactionDetailResponse>>

    @GET("get_detail_transaksi_")
    fun get_detail_transaksi_grafik(): Observable<ListData>
}