package com.work.assignment.utils

import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.snackbar.Snackbar
import com.work.assignment.R
import org.json.JSONObject

object MyHelper {
    fun getExceptionMessage(response: String): String {
        try {
            val obj = JSONObject(response)
            return obj.getString("message")
        } catch (e: Exception) {
            return "Something went wong please try again."
        }
    }

    fun getProgress(context: ImageView): CircularProgressDrawable {
        val progressDrawable = CircularProgressDrawable(context.context)
        progressDrawable.setColorFilter(
            ContextCompat.getColor(context.context, R.color.purple_200),
            PorterDuff.Mode.SRC_IN
        )
        progressDrawable.strokeWidth = 7f
        progressDrawable.centerRadius = 40f
        progressDrawable.start()
        return progressDrawable
    }

    fun shoSnackBar(context: Context, parentLayout: View, message: String) {
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
            .setAction("CLOSE") { }
            .setActionTextColor(ContextCompat.getColor(context, R.color.purple_200))
            .show()
    }


}