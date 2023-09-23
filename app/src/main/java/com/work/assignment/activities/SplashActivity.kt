package com.work.assignment.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.work.assignment.baseUtils.BaseActivity
import com.work.assignment.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setStatusBar()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent =Intent(this@SplashActivity, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivityWithAnimation(intent)
        }, 5000)

    }
}