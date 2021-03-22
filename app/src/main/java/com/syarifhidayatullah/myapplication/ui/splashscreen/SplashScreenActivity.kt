package com.syarifhidayatullah.myapplication.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.syarifhidayatullah.myapplication.R
import com.syarifhidayatullah.myapplication.ui.auth.AuthActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            val auth=Intent(this@SplashScreenActivity,AuthActivity::class.java)
            startActivity(auth)
            finishAffinity()
        },3000)
    }
}