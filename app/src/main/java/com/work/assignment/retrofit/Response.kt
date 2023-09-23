package com.p_bee.retrofit


sealed class Response<T>(val data : T?=null, val errorMessage: String?=null){
    class Loading<T> : Response<T>()
    class Success<T>(response: T?=null) : Response<T>(data = response)
    class Error<T>(errorMessage :String) : Response<T>(errorMessage = errorMessage)

}