package com.work.assignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.p_bee.retrofit.Response
import com.work.assignment.model.ListResModel
import com.work.assignment.retrofit.APIService
import com.work.assignment.utils.MyHelper
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class ImageRepository @Inject constructor(private val apiService: APIService) {

    private val _imageListResponse = MutableLiveData<Response<ListResModel>>()

    val imageListResponse : LiveData<Response<ListResModel>>
        get() = _imageListResponse

    suspend fun myImageList(page:Int, limit:Int) {
        _imageListResponse.postValue(Response.Loading())
        apiService.imageList(page,limit).enqueue(object : Callback<ListResModel> {
            override fun onResponse(
                call: Call<ListResModel>,
                response: retrofit2.Response<ListResModel>
            ) {
                if (response.code() == 200) {
                    if (response.isSuccessful) {
                            _imageListResponse.postValue(Response.Success(response.body()))
                    } else _imageListResponse.postValue(Response.Error(response.message()))
                } else _imageListResponse.postValue(
                    response.errorBody()?.string()?.let { MyHelper.getExceptionMessage(it) }
                        ?.let { Response.Error(it) })
            }

            override fun onFailure(call: Call<ListResModel>, t: Throwable) {
                _imageListResponse.postValue(Response.Error(t.message.toString()))
            }
        })
    }
}