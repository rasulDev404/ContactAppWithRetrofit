package com.example.mycontactretrofit.data.source.remote

import com.example.mycontactretrofit.data.models.ContactData
import com.example.mycontactretrofit.data.models.ContactRequestData
import com.example.mycontactretrofit.data.models.ResponseData
import retrofit2.Call
import retrofit2.http.*

interface ContactApi {

    @GET("/contact")
    fun getAllContacts():Call<ResponseData<List<ContactData>>>

    @DELETE("/contact/{id}")
    fun deleteContactById(@Path("id") contactId:Long):Call<ResponseData<ContactData>>

    @PUT("/contact")
    fun update(@Query("id") contactId:Long,@Body data: ContactRequestData):Call<ResponseData<ContactData>>

    @POST("/contact")
    fun insert(@Body data: ContactRequestData):Call<ResponseData<ContactData>>

}