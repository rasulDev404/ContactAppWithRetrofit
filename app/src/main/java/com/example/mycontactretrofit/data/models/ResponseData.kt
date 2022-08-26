package com.example.mycontactretrofit.data.models

data class ResponseData<T>(
    val message:String,
    val data:T
)
