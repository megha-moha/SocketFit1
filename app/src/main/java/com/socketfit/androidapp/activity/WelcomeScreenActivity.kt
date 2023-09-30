package com.socketfit.androidapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.socketfit.androidapp.databinding.ActivityWelcomeScreenBinding
import com.socketfit.androidapp.ui.swipebtn.OnStateChangeListener
import com.socketfit.androidapp.util.SocketFitApplication


class WelcomeScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.swipeBtn.setOnStateChangeListener(object : OnStateChangeListener {
            override fun onStateChange(active: Boolean) {
                 if(active){
                     if (SocketFitApplication.pref?.getEmail().equals("")) {
                         val i = Intent(this@WelcomeScreenActivity, LoginActivity::class.java)
                         startActivity(i)
                         finish()
                     } else {
                         val i = Intent(this@WelcomeScreenActivity, MainActivity::class.java)
                         startActivity(i)
                         finish()
                     }
                 }
            }
        })

    }

}