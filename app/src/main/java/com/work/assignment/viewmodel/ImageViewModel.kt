package com.work.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.p_bee.retrofit.Response
import com.work.assignment.model.ListResModel
import com.work.assignment.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageViewModel @Inject constructor(private val repository: ImageRepository) : ViewModel() {
    val imageListResponse: LiveData<Response<ListResModel>>
        get() = repository.imageListResponse

    fun imageList(page: Int, limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.myImageList(page, limit)
        }
    }
}