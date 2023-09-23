package com.work.assignment.retrofit

import com.work.assignment.model.ListResModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface APIService {

    @GET(Constants.Partial.getList)
    fun imageList(
        @Query(Constants.Keys.page) page: Int,
        @Query(Constants.Keys.limit) limit: Int
    ): Call<ListResModel>
}
