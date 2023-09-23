package com.work.assignment.baseUtils

import android.content.Intent
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.work.assignment.R


open class BaseActivity : AppCompatActivity() {

    fun setStatusBar() {
        val window = window
        val winParams = window.attributes
        window.attributes = winParams
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = ContextCompat.getColor(this,R.color.transparent)
    }

    fun setWhite() {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
    }

    override fun onBackPressed() {
        super.getOnBackPressedDispatcher().onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }


    fun startActivityWithAnimation(intent: Intent) {
        startActivity(intent)
        overridePendingTransition(R.anim.enter, R.anim.exit)
    }


}