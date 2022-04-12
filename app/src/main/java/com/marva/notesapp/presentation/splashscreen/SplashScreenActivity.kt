package com.marva.notesapp.presentation.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.marva.notesapp.R
import com.marva.notesapp.presentation.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private val splashLongTimeOut: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, splashLongTimeOut)

    }
}