package com.example.mycontactretrofit.repository

import androidx.lifecycle.LiveData
import com.example.mycontactretrofit.data.models.ContactData
import com.example.mycontactretrofit.data.models.ContactRequestData

interface ContactRepository {

    fun add(contact:ContactRequestData):String

    fun addWithoutMessage(contact:ContactRequestData)

    fun edit(id:Int,contact:ContactRequestData)

    fun delete(contact:ContactData)

    fun getContacts(): LiveData<List<ContactData>>

    fun fetchContacts()
}