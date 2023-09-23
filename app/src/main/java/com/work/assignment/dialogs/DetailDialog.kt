package com.work.assignment.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.work.assignment.databinding.DetailsLayoutBinding
import com.work.assignment.model.ListResModelItem

class DetailDialog(
    private val details: ListResModelItem
) :
    BaseDialogFragment<DetailsLayoutBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DetailsLayoutBinding = DetailsLayoutBinding.inflate(layoutInflater)

    companion object {
        fun newInstance(
            detail: ListResModelItem
        ): DetailDialog {
            val args = Bundle()
            val fragment = DetailDialog(detail)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {

        binding.close.setOnClickListener { dismiss() }
        binding.title.text =
            details.author ?: "N/A"
    }
}