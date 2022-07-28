package com.example.monkhoodpractice.SplashActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.monkhoodpractice.Login_SignUp.Login_SignUP
import com.example.monkhoodpractice.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        supportActionBar?.hide()
        binding.splashImage.alpha = 0f
        binding.splashImage.animate().setDuration(2000).alpha(1f).withEndAction{
            startActivity(Intent(this, Login_SignUP::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}