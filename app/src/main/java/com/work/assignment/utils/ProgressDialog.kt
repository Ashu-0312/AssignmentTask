package com.work.assignment.utils

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.work.assignment.R

class ProgressDialog: DialogFragment() {
    companion object {
        fun showLoader(fragmentManager: FragmentManager) {
            try {
                ProgressDialog().show(fragmentManager, "LoaderFragment")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun dismissLoader(fragmentManager: FragmentManager) {
            try {
                (fragmentManager.findFragmentByTag("LoaderFragment") as DialogFragment).dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.dialog_theme)
        isCancelable = false
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val parentView = inflater.inflate(R.layout.loader_layout, container, false)
        dialog!!.window?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(requireContext(),R.color.transparent)))
        return parentView
    }
}