package com.syarifhidayatullah.myapplication.utils

import android.widget.TextView
import com.google.gson.*
import com.syarifhidayatullah.myapplication.model.response.Wrapper
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Helpers {

    fun getDefaultGson(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setDateFormat(Cons.DATE_FORMAT_SERVER)
            .registerTypeAdapter(Date::class.java, JsonDeserializer { json, _,_ ->
                val formatServer = SimpleDateFormat(Cons.DATE_FORMAT_SERVER, Locale.ENGLISH)
                formatServer.timeZone = TimeZone.getTimeZone("UTC")
                formatServer.parse((json.asString))
            })
            .registerTypeAdapter(Date::class.java, JsonSerializer<Date> { src, _, _ ->
                val format = SimpleDateFormat(Cons.DATE_FORMAT_SERVER, Locale.ENGLISH)
                format.timeZone = TimeZone.getTimeZone("UTC")
                if (src != null) {
                    JsonPrimitive(format.format(src))
                } else {
                    null
                }
            })
        //    .setLenient() //ini yg ditambah
            .create()
    }
    fun Throwable.getErrorBodyMessage(): String {
        return if (this is HttpException) {
            val errorCode = this.code()
            if (errorCode == 405) {
                "Method yang digunakan salah"
            } else if (errorCode == 503) {
                "Error Server"
            } else {
                val parseErrorBody = this.response()?.errorBody()!!.parseErrorBody()
                if (parseErrorBody?.meta?.message == null) {
                    "Permintaan anda belum berhasil di proses. Silakan coba kembali"
                } else {
                    parseErrorBody?.meta?.message.toString()
                }
            }

        } else if (this is ConnectException || this is UnknownHostException) {
            "Maaf Anda sedang Offline. Silakan coba kembali"

        } else {
            return if (this.message == null)
                "Permintaan anda belum berhasil di proses. Silakan coba kembali"
            else if (this.message.equals(""))
                ""
            else
                this.message!!

        }
    }

    fun ResponseBody.parseErrorBody(): Wrapper<*>? {
        val gson = Gson()
        val adapter = gson.getAdapter(Wrapper::class.java)
        try {
            return adapter.fromJson(string())
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun TextView.formatPrice(value: String) {
        this.text = getCurrency(java.lang.Double.parseDouble(value))
    }

    fun getCurrency(price: Double): String {
        val format = DecimalFormat("#,###,###")
        return "IDR" + format.format(price).replace(",".toRegex(), ".")
    }
    fun Long.converLongToTime(formatTanggal: String): String {
        val date = Date(this)
        val format = SimpleDateFormat(formatTanggal)
        return format.format(date)
    }
}