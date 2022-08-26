package com.example.mycontactretrofit.repository.impl

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.mycontactretrofit.data.models.ContactData
import com.example.mycontactretrofit.data.models.ContactRequestData
import com.example.mycontactretrofit.data.models.ResponseData
import com.example.mycontactretrofit.data.source.local.AppDatabase
import com.example.mycontactretrofit.data.source.remote.ApiClient
import com.example.mycontactretrofit.data.source.remote.ContactApi
import com.example.mycontactretrofit.repository.ContactRepository
import com.example.mycontactretrofit.utils.hasConnection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class ContactRepositoryImpl : ContactRepository {

    private val contactDao = AppDatabase.instance.contactDao()

    private val contactApi = ApiClient.retrofit.create(ContactApi::class.java)

    override fun add(contact: ContactRequestData): String {
        var message = ""
        if(hasConnection()){
            var response = contactApi.insert(contact).execute()
            if(response.isSuccessful){
                message = if(response.body() != null){
                    val contactData = response.body()!!
                    contactDao.insert(contactData.data)
                    contactData.message
                }else "Body null"
            }else if(response.body() != null){
                message = response.body()!!.message
            }
        }else{
            message = "Internet yo'q"
        }
        return message
    }

    override fun addWithoutMessage(contact: ContactRequestData) {
        if(hasConnection()){
            contactApi.insert(contact).enqueue(object :Callback<ResponseData<ContactData>>{
                override fun onResponse(
                    call: Call<ResponseData<ContactData>>,
                    response: Response<ResponseData<ContactData>>
                ) {
                    if(response.body()!= null){
                        val contactData = response.body()!!
                        contactDao.insert(contactData.data)
                        contactData.message
                    }
                }

                override fun onFailure(call: Call<ResponseData<ContactData>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    override fun edit(id: Int, contact: ContactRequestData) {
        TODO("Not yet implemented")
    }

    override fun delete(contact: ContactData) {
        TODO("Not yet implemented")
    }

    override fun getContacts(): LiveData<List<ContactData>> = contactDao.getAllContacts()

    override fun fetchContacts() {
        if(hasConnection()){
            contactApi.getAllContacts().enqueue(object :Callback<ResponseData<List<ContactData>>>{
                override fun onResponse(
                    call: Call<ResponseData<List<ContactData>>>,
                    response: Response<ResponseData<List<ContactData>>>
                ) {
                    if(response.isSuccessful){
                        if(response.body() != null){
                            val responseData = response.body()!!
                            contactDao.updateAllContact(responseData.data)
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseData<List<ContactData>>>, t: Throwable) {
                    Timber.d(t.message)
                }

            })
        }
    }
}