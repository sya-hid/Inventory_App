package com.syarifhidayatullah.myapplication

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.multidex.MultiDexApplication
import com.syarifhidayatullah.myapplication.network.HttpClient
import com.syarifhidayatullah.myapplication.utils.Cons.SharePreferenceConfig.PREFERENCES_TOKEN
import com.syarifhidayatullah.myapplication.utils.Cons.SharePreferenceConfig.PREFERENCES_USER

class Dashnet : MultiDexApplication() {

    companion object {
        lateinit var instance: Dashnet

        fun getApp(): Dashnet {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(this)
    }

    fun setToken(token: String) {
        getPreferences().edit().putString(PREFERENCES_TOKEN, token).apply()
        HttpClient.getInstance().buildRetrofitClient(token)
    }

    fun getToken(): String? {
        return getPreferences().getString(PREFERENCES_TOKEN, null)
    }

    fun setUser(user: String) {
        getPreferences().edit().putString(PREFERENCES_USER, user).apply()
        //HttpClient.getInstance().buildRetrofitClient(user)

    }

    fun getUser(): String? {
        return getPreferences().getString(PREFERENCES_USER, null)

    }

    fun clearToken() {
        getPreferences().edit().remove(PREFERENCES_TOKEN).apply()
        HttpClient.getInstance().buildRetrofitClient("")
    }
}